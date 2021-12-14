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
    dataTab = document.getElementsByClassName("data-tab");
    for (i = 0; i < dataTab.length; i++) {
        dataTab[i].className = dataTab[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(selectedDataSource).style.display = "block";
    evt.currentTarget.className += " active";
}