// Adds new rows to table with id=id;
function addRow(id){

        var table = document.getElementById(id);
        var tbody = table.getElementsByTagName("TBODY")[0];
        var lastRow = table.rows[table.rows.length - 1];
        var name = lastRow.getElementsByTagName("td")[0].getElementsByTagName("input")[0].name;

        var index = (name.match(/\d+/)); // Finds index of lastRow. (books[0].title, index=0);

        var prefix = name.substring(0,name.indexOf(index)); // books[

        var row = document.createElement("TR");
        for (i=0; i<lastRow.cells.length-1; i++){
            var nameOfProperty = lastRow.cells[i].firstElementChild.name;
            var suffix = findSuffix(prefix, index, nameOfProperty);
            var newIndex = String(Number(index) + 1);
            var nameOfProperty = prefix + newIndex + suffix;
            var element = document.createElement("TD");
            var input = document.createElement("INPUT");
            input.id = prefix.substring(0, prefix.length-1) + newIndex + suffix.substring(1);
            input.name = nameOfProperty;
            element.appendChild(input);
            row.appendChild(element);
        }

        var button = document.createElement("BUTTON");
        button.innerHTML = "+";
        element = document.createElement("TD");
        element.appendChild(button);
        row.appendChild(element);

        tbody.appendChild(row);
  }

  function findSuffix(prefix, index, name){
    return name.substring(name.indexOf(index)+String(index).length);
  }

  function cloneRow(tableToModify,thisElement) {
    var row = thisElement.parentElement.parentElement; // find row to copy
    var table = document.getElementById(tableToModify); // find table to append to
    alert(table.id);
    var clone = row.cloneNode(true); // copy children too
    table.getElementsByTagName("TBODY").appendChild(clone); // add new row to end of table
  }