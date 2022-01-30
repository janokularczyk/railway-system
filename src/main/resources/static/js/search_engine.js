let btnSearch = document.getElementById('search');

btnSearch.addEventListener('click', function generateUrl() {

    let inputFrom = document.getElementById('from').value;
    let inputTo = document.getElementById('to').value;
    console.log(inputFrom);
    let mod = "?to="  + inputTo + "&from=" + inputFrom;
    let base = 'http://localhost:8080/connections';

    const url = new URL(mod, base);
    window.open(url, '_blank');
});