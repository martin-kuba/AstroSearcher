function btnToLoadingStatus(evt) {
    document.getElementById("btn-search-text").innerText = "Searching...";
    document.getElementById('spinner').removeAttribute("hidden");
    document.getElementById("btn-search").disabled = true;
}