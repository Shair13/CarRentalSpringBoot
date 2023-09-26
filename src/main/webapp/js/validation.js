//use add
const userAdminAddForm = document.querySelector('#userAdminAddForm');
const emailInput = document.querySelector('input#floatingEmail');
const nameInput = document.querySelector("input#floatingFirstName");
const surnameInput = document.querySelector('input#floatingLastName');
const passwordInput = document.querySelector('input#floatingPassword');
const repeatPasswordInput = document.querySelector('input#floatingRepeatPassword');
//errors
const errorMessage = document.querySelector('div#error-message');
//car add & update
const carBrandInput = document.querySelector("input#floatingBrand");
const carModelInput = document.querySelector("input#floatingModel");
const carTypeSelect = document.querySelector("#floatingType");
const carProductionInput = document.querySelector("input#floatingProdDate");
const carPlatesInput = document.querySelector("input#floatingPlates");
const carPriceInput = document.querySelector("input#floatingPrice");
const carMileageInput = document.querySelector("input#floatingMileage");
const carInspectionInput = document.querySelector("input#floatingInspection");
const carInsuranceInput = document.querySelector("input#floatingInsurance");
const carCapacityInput = document.querySelector("input#floatingCapacity");
const carVinInput = document.querySelector("input#floatingVin");
const carAddForm = document.querySelector('#carAddForm');
const carUpdateForm = document.querySelector('#carUpdateForm')
const carStatusInput = document.querySelector("input#floatingStatus");

//ADMIN user add validation
if (userAdminAddForm !== null) {
    userAdminAddForm.addEventListener('submit', onUserFormSubmission);

    function onUserFormSubmission(event) {

        const errors = [];

        if (!emailInput.value.includes('@')) {
            errors.push('Email musi posiadać znak @');
        }
        if (nameInput.value.length <= 2) {
            errors.push("Twoje imię jest za krótkie");
        }
        if (surnameInput.value.length <= 2) {
            errors.push("Twoje nazwisko jest za krótkie");
        }
        if (passwordInput.value.length <= 4 || passwordInput.value !== repeatPasswordInput.value) {
            errors.push("Hasła nie są takie same lub mają mniej niż 5 znaków");

        }

        if (errors.length === 0) {
            errorMessage.classList.add("d-none");
        } else {
            errorMessage.innerText = errors.join(", ");
            errorMessage.classList.remove("d-none");
            event.preventDefault();
        }
    }
}

//ADMIN user edit validation



//car add validation

if (carAddForm !== null) {
    carAddForm.addEventListener('submit', onCarAddFormSubmission);

    function onCarAddFormSubmission(event) {

        const errors = [];

        if (carBrandInput.value.length === 0) {
            errors.push('Podaj markę');
        }
        if (carModelInput.value.length === 0) {
            errors.push('Podaj model');
        }
        if (carTypeSelect.value.length === 0) {
            errors.push('Wybierz typ nadwozia');
        }
        if (carPlatesInput.value === 0) {
            errors.push('Podaj tablice rejestracyjne');
        }
        if (carPriceInput.value < 0) {
            errors.push('Cena nie może być mniejsza od 0');
        }
        if (carMileageInput.value < 0) {
            errors.push('Przebieg nie może być mniejszy od 0');
        }
        if (carCapacityInput.value <= 0) {
            errors.push('Pojemność silnika nie może być mniejsza lub równa 0');
        }
        if (carVinInput.value.length !== 17) {
            errors.push('VIN musi mieć 17 znaków');
        }
        if (carProductionInput.value.includes('d', 'M', 'y') || carProductionInput.value.length !== 10) {
            errors.push('Data produkcji: podaj poprawną datę')
        }
        if (carInsuranceInput.value.includes('d', 'M', 'y') || carInsuranceInput.value.length !== 10) {
            errors.push('Data ubezpieczenia: podaj poprawną datę')
        }
        if (carInspectionInput.value.includes('d', 'M', 'y') || carInspectionInput.value.length !== 10) {
            errors.push('Data przeglądu: podaj poprawną datę')
        }

        if (errors.length === 0) {
            errorMessage.classList.add('d-none');
        } else {
            errorMessage.innerText = errors.join(', ');
            errorMessage.classList.remove('d-none');
            event.preventDefault();
        }
    }
}

//car update validation

if (carUpdateForm !== null) {
    carUpdateForm.addEventListener('submit', onCarUpdateFormSubmission);

    function onCarUpdateFormSubmission(event) {

        const errors = [];

        if (carBrandInput.value.length === 0) {
            errors.push('Podaj markę');
        }
        if (carModelInput.value.length === 0) {
            errors.push('Podaj model');
        }
        if (carTypeSelect.value.length === 0) {
            errors.push('Wybierz typ nadwozia');
        }
        if (carPlatesInput.value === 0) {
            errors.push('Podaj tablice rejestracyjne');
        }
        if (carPriceInput.value < 0) {
            errors.push('Cena nie może być mniejsza od 0');
        }
        if (carMileageInput.value < 0) {
            errors.push('Przebieg nie może być mniejszy od 0');
        }
        if (carCapacityInput.value <= 0) {
            errors.push('Pojemność silnika nie może być mniejsza lub równa 0');
        }
        if (carVinInput.value.length !== 17) {
            errors.push('VIN musi mieć 17 znaków');
        }
        if (carProductionInput.value.includes('d', 'M', 'y') || carProductionInput.value.length !== 10) {
            errors.push('Data produkcji: podaj poprawną datę')
        }
        if (carInsuranceInput.value.includes('d', 'M', 'y') || carInsuranceInput.value.length !== 10) {
            errors.push('Data ubezpieczenia: podaj poprawną datę')
        }
        if (carInspectionInput.value.includes('d', 'M', 'y') || carInspectionInput.value.length !== 10) {
            errors.push('Data przeglądu: podaj poprawną datę')
        }
        if(!carStatusInput.value.includes('serwis', 'zarezerwowany', 'dostępny')){
            errors.push('Podaj prawidłowy status')
        }

        if (errors.length === 0) {
            errorMessage.classList.add('d-none');
        } else {
            errorMessage.innerText = errors.join(', ');
            errorMessage.classList.remove('d-none');
            event.preventDefault();
        }
    }
}