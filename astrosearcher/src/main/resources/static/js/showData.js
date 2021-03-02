//TODO: Rework this script completely (view mainly)
function showData(evt, selectedDataSource) {
    // Declare all variables
    var i, tabcontent, datasource;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    datasource = document.getElementsByClassName("datasource");
    for (i = 0; i < datasource.length; i++) {
        datasource[i].className = datasource[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(selectedDataSource).style.display = "block";
    evt.currentTarget.className += " active";
}