function exportToCsv(text_csv){
    var textFileAsBlob = new Blob([text_csv], {type:'text/plain'});

    var downloadLink = document.createElement("a");
    downloadLink.download = 'file.csv';
    downloadLink.innerHTML = "Download File";
    if (window.webkitURL != null)
    {
        downloadLink.href = window.webkitURL.createObjectURL(textFileAsBlob);
    }
    else
    {
        downloadLink.href = window.URL.createObjectURL(textFileAsBlob);
        downloadLink.onclick = destroyClickedElement;
        downloadLink.style.display = "none";
        document.body.appendChild(downloadLink);
    }
    downloadLink.click();
}
