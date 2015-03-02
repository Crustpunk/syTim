/**
 * 
 * J.Arrasz helper JS Methods for sytim.
 */

var syncContent = "";

function localDelete() {
    alert('localDelete');
}

function remoteDelete() {
    alert('remoteDelete');
}


/**
 * Saves the whole time entry received from the web form.
 * TODO: Validation?
 */
function saveTimeEntry() {
    //validation????
    var project = document.getElementById("project").value;
    if (localStorage.getItem(project)) {// this project is already saved
        var projectData = JSON.parse(localStorage.getItem(project));

        projectData.timeentries.push(createSubEntry());

        localStorage.setItem(project, JSON.stringify(projectData));
    } else { //first entry for this project
        var projectData = {project: project, timeentries: [createSubEntry()]};
        localStorage.setItem(project, JSON.stringify(projectData));
    }
    // wir brauchen noch eine success message, fail message und ein clearing der ganzen Elemente
    addSuccessMessage("Zeit erfolgreich gespeichert!");
    //clear form?
}

function test() {
    console.log('start patch');
    patchResponseData('{"project":"Artikel 3","timeentries":[{"when":"2014-10-30","from":"08:30","to":"12:30","desc":"Finalisiert und abgeschickt"}]}',
            '{"project":"Artikel 2","timeentries":[{"when":"2014-10-30","from":"08:30","to":"12:30","desc":"Finalisiert und abgeschickt"}]}');
}


function checkDataAndServer() {

    var ident = checkIdentifier();
    var localData = getContentFromLocalStore();
    $.ajax({
        url: 'http://localhost:8080/data',
        type: 'GET',
        datatype: 'json',
        contentType: 'application/json',
        data: ident,
        success: function (response) {
            patchResponseData(response, localdata);
            gotDatafromServer();
        }

    });

    //TODO we want to check if we have an identifier
    //we want to check if there is data with this identifier
    //we want to inform a new user that he can pull data with a known identifier
    //we want to grab the data, create a JSONPATCH and then show it to the user
    //we want to interact with the user (write data and so on) 
}

function patchResponseData(response, localdata) {

    var jiff = require(['js/vendor/jiff.js']);
    var diffResult = jiff.diff(response, localdata);
}

function gotDatafromServer() {
    alert("Daten vom Server gesynced");
}

/**
 * Starts the sync to and from server.
 * 
 * @returns {undefined}
 */
function sync() {

    var ident = checkIdentifier();
    console.log("Sync for " + ident);
    getContentFromLocalStore();
    transformSyncContent(ident);


    $.ajax({
        url: 'http://localhost:8080/sync',
        type: 'POST',
        datatype: 'json',
        contentType: 'application/json',
        data: syncContent,
        success: function (response) {
            createSyncSuccess(response);
        }

    });
    syncSuccess = "";
}

function transformSyncContent(ident) {
    //we need to create a JSON String corresponding to backend entities.
    var finalSyncContent;
    //we create the projects JSON
    finalSyncContent = '{\"ident\":\"' + ident + '\" , \"projects\": [' + syncContent + ']}';

    syncContent = finalSyncContent;
    console.log(syncContent);
}

/**
 * Creates a custom successmessage or sync success.
 * 
 * @param {type} response
 * @returns {undefined}
 */
function createSyncSuccess(response) {
    writeSyncedDataFromServer(response);
    addSuccessMessage("Sync erfolgreich!");
    syncContent = "";

}

/**
 * Writes the data received from server into localstore. 
 * TODO
 * @param {type} response
 * @returns {undefined}
 */
function writeSyncedDataFromServer(response) {
    console.log(response);
}

/**
 * Read all for this app relevant content from localstore.
 * 
 * @returns {undefined}
 */
function getContentFromLocalStore() {

    return readTimeEntries(returnTimeEntries);

}

/**
 * Checks if there is an identifier for this machine. If yes we return it otherwise we create
 * one and returns this new created.
 * 
 * @returns {Number|String|createIdent.m|createIdent.u|createIdent.d|Date|createIdent.c|DOMString}
 */
function checkIdentifier() {
    console.log("start check the identifier");
    var identifier = "NON";
    if (!localStorage.getItem("ident")) {
        var identifier = createIdent();
        localStorage.setItem("ident", identifier);
        console.log("created a unique identifier for this client: " + identifier);
    } else {
        identifier = localStorage.getItem("ident");
    }
    updateJumboTronWith(identifier);
    return identifier;
}

function deleteContent() {
    localStorage.clear();
}

function updateJumboTronWith(ident) {
    if (document.getElementById("jumbo")) {

        var jumbotron = document.getElementById("headline");
        if (jumbotron !== null)
            jumbotron.innerHTML = jumbotron.innerHTML + " -- Du bist " + ident;
    }
}

function newIdent() {
    var newIdentifier = document.getElementById("ident").value;
    localStorage.setItem("ident", newIdentifier);
}

/**
 * Creates a unique Identifier for this machine.
 * 
 * @returns {String|Number|createIdent.d|Date}
 */
function createIdent() {
    var c = 1;

    var d = new Date(),
            m = d.getMilliseconds() + "",
            u = ++d + m + (++c === 10000 ? (c = 1) : c);

    return u;

}

/**
 * Inserts a successmessage panel with the gien message to the jumbotron.
 * 
 * @param {type} message the message we use in the message panel
 * @returns {undefined}
 */
function addSuccessMessage(message) {
    //check if there is already a successmessage div ... 
    if (document.getElementById("successmessage")) { //there is already one
        //what to do
    } else {
        var success = document.createElement('div');
        success.className = "alert alert-success";
        success.innerHTML = message;
        success.id = "successmessage";
        var root = document.getElementById("jumbo");
        root.appendChild(success);
    }
}

/**
 * Craetes a time entry from the web form.
 * 
 * @returns {String} the created time entry
 */
function createSubEntry() {
    //validation ??? 
    var when = document.getElementById("when").value;
    var from = document.getElementById("from").value;
    var to = document.getElementById("to").value;
    var desc = document.getElementById("description").value;
    var timeEntryObject = {when: when, from: from, to: to, desc: desc};
    console.log("added a time entry for syTim");
    return timeEntryObject;
}

/**
 * Reads all time entries and injects them into the method toBeCalled.
 * 
 * @param {type} toBeCalled method you can inject to be used for all read time entries
 * @returns {undefined} nothing
 */
function readTimeEntries(toBeCalled) {
    console.log("found " + (localStorage.length - 1) + " projects for syTim"); //special 'ident' ... -1 ;-)
    for (var i = 0; i < localStorage.length; i++) {
        var key = localStorage.key(i);
        if (key.toString() !== 'ident') {
            var data = localStorage.getItem(key);
            toBeCalled(key, data);
        }
    }
}

/**
 * Creates the data to sync with our needed protocol.
 * 
 * @param {type} key
 * @param {type} data
 * @returns {undefined}
 */
function returnTimeEntries(key, data) {
    console.log("read time entries to sync em: " + data);
//    syncContent += "{";
//    syncContent += key;
//    syncContent += ":";
//    syncContent += data;
    if (syncContent !== undefined) {
        syncContent += ',';
        syncContent += data;
    }
}

/**
 * Renders all time entries to our web form.
 * 
 * @param {type} key
 * @param {type} data
 * @returns {undefined}
 */
function renderTimeEntries(key, data) {
    data = JSON.parse(data);
    console.log(data);
    var root = document.getElementById("projectview");

    var panel = document.createElement('div');
    panel.className = "panel panel-info";

    var panelHeading = document.createElement('p');
    panelHeading.className = "panel-heading";
    panelHeading.innerHTML = data.project;

    panel.appendChild(panelHeading);

    var panelBody = document.createElement('table');
    panelBody.className = "table table-hover";

    var tBody = document.createElement('tbody');
    panelBody.appendChild(tBody);

    for (var i = 0; i < data.timeentries.length; i++) {

        var bodyLine = document.createElement('tr');
        tBody.appendChild(bodyLine);

        var bodyWhen = document.createElement('td');
        bodyWhen.className = "col-xs-2";
        bodyWhen.innerHTML = data.timeentries[i].when; //"23.11.2013";


        var bodyFrom = document.createElement('td');
        bodyFrom.className = "col-xs-2";
        bodyFrom.innerHTML = data.timeentries[i].from; //"11:15";

        var bodyTo = document.createElement('td');
        bodyTo.className = "col-xs-2";
        bodyTo.innerHTML = data.timeentries[i].to; //"13:15";

        var bodyDesc = document.createElement('td');
        bodyDesc.className = "col-xs-6";
        bodyDesc.innerHTML = data.timeentries[i].desc; //"a description";

        bodyLine.appendChild(bodyWhen);
        bodyLine.appendChild(bodyFrom);
        bodyLine.appendChild(bodyTo);
        bodyLine.appendChild(bodyDesc);


        tBody.appendChild(bodyLine);
    }
    panel.appendChild(panelBody);
    root.appendChild(panel);

}


