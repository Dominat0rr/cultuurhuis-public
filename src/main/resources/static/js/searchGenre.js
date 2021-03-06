const search = document.querySelector('#search');
const originalList = document.querySelector('#original-list');
const matchList = document.querySelector('#match-list');

let genres = document.querySelectorAll(".list-group-item > a");
genres = Array.from(genres);


const searchGenres = searchText => {
    let matches = [];
   if (searchText.length === 0) {
        matches = [];
        outputHtmlOriginal(matches);
        return;
    }

    matches = genres.filter((genre) => {
        const regex = new RegExp(`^${searchText}`, 'gi');
        return genre.text.match(regex);
    });

    outputHtml(matches);
};

const outputHtmlOriginal = () => {
        matchList.innerHTML = '';
        document.getElementById("original-list").style.display = "block";
}

const outputHtml = matches => {
        console.log("blub");
        document.getElementById("original-list").style.display = "none";
        const html = matches.map(match => `
            <ul class="list-group mt-2">
                 <li class="list-group-item">
                    <a href=/genres/${match.getAttribute('value')} text=${match.text} value=${match.getAttribute('value')}>${match.text}</a>
                 </li>
            </ul>
        `
        ).join('');

        matchList.innerHTML = html;
};

search.addEventListener('input', () => searchGenres(search.value));