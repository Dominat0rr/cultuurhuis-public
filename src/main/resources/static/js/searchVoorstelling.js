const search = document.querySelector('#search');
const originalList = document.querySelector('#original-list');
const matchList = document.querySelector('#match-list');

let voorstellingen = document.querySelectorAll(".list-group-item > a");
voorstellingen = Array.from(voorstellingen);


const searchVoorstellingen = searchText => {
    let matches = [];
   if (searchText.length === 0) {
        matches = [];
        outputHtmlOriginal(matches);
        return;
    }

    matches = voorstellingen.filter((voorstelling) => {
        const regex = new RegExp(`^${searchText}`, 'gi');
        return voorstelling.text.match(regex);
    });
    console.log(matches);

    outputHtml(matches);
};

const outputHtmlOriginal = () => {
        matchList.innerHTML = '';
        document.getElementById("original-list").style.display = "block";
}


const outputHtml = matches => {
        document.getElementById("original-list").style.display = "none";
        const html = matches.map(match => `
            <ul class="list-group mt-2">
                 <li class="list-group-item">
                    <a href=/voorstellingen/${match.getAttribute('value')} text=${match.text} value=${match.getAttribute('value')}>${match.text}</a>
                 </li>
            </ul>
        `
        ).join('');

        matchList.innerHTML = html;
};

search.addEventListener('input', () => searchVoorstellingen(search.value));