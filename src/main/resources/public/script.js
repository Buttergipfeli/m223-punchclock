const URL = 'http://localhost:8081';
let entries = [];

const dateAndTimeToDate = (dateString, timeString) => {
    return new Date(`${dateString}T${timeString}`).toISOString();
};

const deleteEntry = (id) => {
    fetch(`${URL}/entries/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
    }).then((result) => {
        result.json().then((result) => {
            entries = result;
            renderEntries();
        });
    });
}

const updateEntry = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    entry['checkIn'] = dateAndTimeToDate(formData.get('checkInDate'), formData.get('checkInTime'));
    entry['checkOut'] = dateAndTimeToDate(formData.get('checkOutDate'), formData.get('checkOutTime'));
    entry['id'] = formData.get('idUpdate');

    fetch(`${URL}/entries`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entry)
    }).then((result) => {
        const errorMessage = document.getElementById("errorMessage");
        if (result.status !== 200) {
            errorMessage.innerText = "Check can't be after Check out";
        } else {
            result.json().then((results) => {
                errorMessage.innerText = "";
                entries = results;
                renderEntries();
            });
        }
    });
}

const createEntry = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    entry['checkIn'] = dateAndTimeToDate(formData.get('checkInDate'), formData.get('checkInTime'));
    entry['checkOut'] = dateAndTimeToDate(formData.get('checkOutDate'), formData.get('checkOutTime'));

    fetch(`${URL}/entries`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entry)
    }).then((result) => {
        const errorMessage = document.getElementById("errorMessage");
        if (result.status !== 201) {
            errorMessage.innerText = "Check can't be after Check out";
        } else {
            result.json().then((entry) => {
                errorMessage.innerText = "";
                entries.push(entry);
                renderEntries();
            });
        }
    });
};

const indexEntries = () => {
    fetch(`${URL}/entries`, {
        method: 'GET'
    }).then((result) => {
        result.json().then((result) => {
            entries = result;
            renderEntries();
        });
    });
    renderEntries();
};

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

const createUpdateForm = (id) => {
    const updateForm = document.querySelector('#updateEntryForm');
    const updateInputId = document.createElement('input');
    updateInputId.setAttribute("id", "idUpdate");
    updateInputId.setAttribute("name", "idUpdate");
    updateInputId.setAttribute("type", "number");
    updateInputId.setAttribute("value", `${id}`)
    updateForm.appendChild(updateInputId);
    updateForm.setAttribute("class", "updateFormNotHidden");
}

const renderEntries = () => {
    const display = document.querySelector('#entryDisplay');
    display.innerHTML = '';
    entries.forEach((entry) => {
        const row = document.createElement('tr');
        const button = document.createElement('button');
        button.setAttribute("id", "deleteBtn");
        button.setAttribute("onclick", "deleteEntry(" + entry.id + ")");
        const updateButton = document.createElement('button');
        updateButton.setAttribute("id", "updateButton");
        updateButton.setAttribute("onclick", `createUpdateForm(${entry.id})`);
        row.appendChild(createCell(entry.id));
        row.appendChild(createCell(new Date(entry.checkIn).toLocaleString()));
        row.appendChild(createCell(new Date(entry.checkOut).toLocaleString()));
        button.appendChild(createCell("Delete"));
        row.appendChild(button);
        updateButton.appendChild(createCell("Update"));
        row.appendChild(updateButton);
        display.appendChild(row);
    });
};

document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#createEntryForm');
    createEntryForm.addEventListener('submit', createEntry);
    const updateEntryForm = document.querySelector('#updateEntryForm');
    updateEntryForm.addEventListener('submit', updateEntry);
    indexEntries();
});