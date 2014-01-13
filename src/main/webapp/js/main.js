function saveTimeEntry() {
    //validation????
    var project = document.getElementById("project").value;
    
    if (localStorage.getItem(project)) {// this project is already saved
        var projectData = localStorage.getItem(project);
        localStorage.setItem(project, projectData + "|" + createSubEntry());
    } else { //first entry for this project

        localStorage.setItem(project, createSubEntry());
    }
    // wir brauchen noch eine success message, fail message und ein clearing der ganzen Elemente
    addSuccessMessage();
    //clear form?
}

function sync() {
    
    var ident = checkIdentifier();
    console.log("Sync for " + ident);
    var content = 
    $.ajax({
        url: 'http://localhost:8080/sync',
        type: 'POST',
        data: 'ident=' + ident + '&content=' + content, // or $('#myform').serializeArray()
        success: function() { alert('POST completed'); }
    });
}

function checkIdentifier() {
    console.log("start check the identifier");
    var identifier = "NON";
    if(!localStorage.getItem("ident")) {
        var identifier = createIdent();
        localStorage.setItem("ident", identifier);
        console.log("created a unique identifier for this client: " + identifier);
    } else {
        identifier = localStorage.getItem("ident");
    }
    return identifier;
}

function createIdent() {
    var c = 1;
    
        var d = new Date(),
            m = d.getMilliseconds() + "",
            u = ++d + m + (++c === 10000 ? (c = 1) : c);

        return u;
    
}

function addSuccessMessage() {
    console.log("successmessage");
    var success = document.createElement('div');
    success.className = "alert alert-success";
    success.innerHTML = "Erfolgreich gespeichert!";
    var root = document.getElementById("jumbo");
    root.appendChild(success);
}


function createSubEntry() {
    //validation ??? 
    var when = document.getElementById("when").value;
    var from = document.getElementById("from").value;
    var to = document.getElementById("to").value;
    var desc = document.getElementById("description").value;
    var timeEntryObject = {when: when, from: from, to: to, desc: desc};
    console.log("added a time entry for syTim");
    return JSON.stringify(timeEntryObject);
}

function readTimeEntries() {
    console.log("found " + (localStorage.length -1) + " projects for syTim"); //special ident ... -1 ;-)
    for (var i = 0; i < localStorage.length; i++) {
        var key = localStorage.key(i);
        if(key.toString() !== 'ident') {
            var data = localStorage.getItem(key);
            renderTimeEntries(key, data);
        }
    }
}

function renderTimeEntries(key, data) {
    console.log("try to render all projectdata for " + key);
    var root = document.getElementById("projectview");
    
    data = data.split("|");

    var panel = document.createElement('div');
    panel.className = "panel panel-info";

    var panelHeading = document.createElement('p');
    panelHeading.className = "panel-heading";
    panelHeading.innerHTML = key;

    panel.appendChild(panelHeading);

    var panelBody = document.createElement('table');
    panelBody.className = "table table-hover";

    var tBody = document.createElement('tbody');
    panelBody.appendChild(tBody);

    //from here we should iterate
    for (var i = 0; i < data.length; i++) {
        var tmp = JSON.parse(data[i]);
        console.log(tmp);
        var bodyLine = document.createElement('tr');
        tBody.appendChild(bodyLine);

        var bodyWhen = document.createElement('td');
        bodyWhen.className = "col-xs-2";
        bodyWhen.innerHTML = tmp.when; //"23.11.2013";
        

        var bodyFrom = document.createElement('td');
        bodyFrom.className = "col-xs-2";
        bodyFrom.innerHTML = tmp.from; //"11:15";

        var bodyTo = document.createElement('td');
        bodyTo.className = "col-xs-2";
        bodyTo.innerHTML = tmp.to; //"13:15";

        var bodyDesc = document.createElement('td');
        bodyDesc.className = "col-xs-6";
        bodyDesc.innerHTML = tmp.desc; //"a description";

        bodyLine.appendChild(bodyWhen);
        bodyLine.appendChild(bodyFrom);
        bodyLine.appendChild(bodyTo);
        bodyLine.appendChild(bodyDesc);


        tBody.appendChild(bodyLine);
    }
    panel.appendChild(panelBody);
    root.appendChild(panel);

}



