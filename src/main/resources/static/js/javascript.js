// Adds new rows to table with id=id;
function addRow(id){

        var table = document.getElementById(id);
        var lastRow = table.rows[table.rows.length - 1];

        cloneRow(id,lastRow.firstElementChild.firstElementChild); //Copy of lastRow
        cleanValuesOfInput(table.rows[table.rows.length - 1]);
  }

  function findSuffix(name){
    var index = findIndexFromName(name);
    return name.substring(name.indexOf(index)+String(index).length);
  }

  function findPrefix(name){
    var index = findIndexFromName(name);
    return name.substring(0,name.indexOf(index));
  }

  function cleanValuesOfInput(lastRow){
        for (i=0; i<lastRow.cells.length-1; i++){
            var td = lastRow.cells[i];
            element = td.firstElementChild;
            element.value = null;
        }
  }

  function findIndexFromName(name){
    var str = String(name.match(/\[\d+\]/));
    var number = str.substring(1,str.length-1);
    return number; // Finds index of Row. (books[0].title, index=0);
  }

  function findLastRowIndex(table){
    return table.rows[table.rows.length - 1];
  }

  function findNewIndex(table){
    var tableRows = table.rows;
    var lastRow = tableRows[tableRows.length-1];
    var td = lastRow.firstElementChild;
    var input = td.firstElementChild;
    var name = input.name;
    var index = findIndexFromName(name);
    return ++index;
  }



  function cloneRow(tableToModifyId,thisElement) {
    var row = thisElement.parentElement.parentElement; // find row to copy
    var table = document.getElementById(tableToModifyId);
    var tbody = table.getElementsByTagName("TBODY")  // find table to append to
    var clone = row.cloneNode(true); // copy children too


    var nameOfProperty = clone.firstElementChild.firstElementChild.name;
    var prefix = findPrefix(nameOfProperty);
        for (i=0; i<clone.cells.length-1; i++){

                    var td = clone.cells[i];
                    var input = td.firstElementChild;
                    var nameOfProperty = input.name;
                    var suffix = findSuffix(nameOfProperty);
                    var newIndex = findNewIndex(table);
                    var nameOfProperty = prefix + newIndex + suffix;
                    if (i==4) {
                        input.value = row.cells[4].firstElementChild.value;
                    }

                    input.name = nameOfProperty;
                    input.id = prefix.substring(0, prefix.length-1) + newIndex + suffix.substring(1);
                }


    var tbody = table.getElementsByTagName("TBODY")[0];
        tbody.appendChild(clone);// add new row to end of table

  }