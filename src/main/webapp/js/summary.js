document.addEventListener("DOMContentLoaded", function () {

    function summary() {
        var quantity = document.forms['form1']['quantity'].value;
        var institution = document.forms['form1']['institution'].value;
        var street = document.forms['form1']['street'].value;
        var city = document.forms['form1']['city'].value;
        var zipCode = document.forms['form1']['zipCode'].value;
        var phoneNumber = document.forms['form1']['phoneNumber'].value;
        var pickUpDate = document.forms['form1']['pickUpDate'].value;
        var pickUpTime = document.forms['form1']['pickUpTime'].value;
        var pickUpComment = document.forms['form1']['pickUpComment'].value;

        var placeForQuantity = document.querySelector('#placeForQuantity');
        placeForQuantity.innerText = quantity + " worki z kategorii " ;

        var placeForInstitution = document.querySelector('#placeForInstitution');
        placeForInstitution.innerText = "Dla instytucji: " + institution;

        var placeForStreet = document.querySelector('#placeForStreet');
        placeForStreet.innerText = street;

        var placeForCity = document.querySelector('#placeForCity');
        placeForCity.innerText = city;

        var placeForZipCode = document.querySelector('#placeForZipCode');
        placeForZipCode.innerText = zipCode;

        var placeForStreet = document.querySelector('#placeForStreet');
        placeForStreet.innerText = street;

        var placeForPhoneNumber = document.querySelector('#placeForPhoneNumber');
        placeForPhoneNumber.innerText = phoneNumber;

        var placeForPickUpDate = document.querySelector('#placeForPickUpDate');
        placeForPickUpDate.innerText = pickUpDate;

        var placeForPickUpTime = document.querySelector('#placeForPickUpTime');
        placeForPickUpTime.innerText = pickUpTime;

        var placeForPickUpComment = document.querySelector('#placeForPickUpComment');
        placeForPickUpComment.innerText = pickUpComment;
    }

});

