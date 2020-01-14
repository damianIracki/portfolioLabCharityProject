<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>


</head>
<body>
<%@include file="/WEB-INF/donationFormHeader.jspf"%>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Ważne!</h3>
            <p data-step="1" class="active">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="2">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="3">
                Wybierz jedną, do
                której trafi Twoja przesyłka.
            </p>
            <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <form:form modelAttribute="donation" method="post" name="form1">
            <!-- STEP 1: class .active is switching steps -->
            <div data-step="1" class="active">
                <h3>Zaznacz co chcesz oddać:</h3>
                <c:forEach items="${categories}" var="category">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <form:checkbox path="categories" value="${category}"/>
                            <span class="checkbox"></span>
                            <span class="description">${category.name}</span>
                        </label>
                    </div>
                </c:forEach>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 2 -->
            <div data-step="2">
                <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

                <div class="form-group form-group--inline">
                    <label>
                        Liczba 60l worków:
                        <form:input id="quantity" path="quantity"/>
                    </label>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step" >Dalej</button>
                </div>
            </div>



            <!-- STEP 3 -->
            <div data-step="3">
                <h3>Wybierz organizacje, której chcesz pomóc:</h3>

                <c:forEach items="${institutions}" var="institution">
                <div class="form-group form-group--checkbox">
                    <label>
                        <form:radiobutton path="institution" value="${institution.name}"/>
                        <span class="checkbox radio"></span>
                        <span class="description">
                            <div class="title">${institution.name}</div>
                            <div class="subtitle">${institution.description}</div>
                        </span>
                    </label>
                </div>
                </c:forEach>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 5 -->
            <div data-step="4">
                <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Adres odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label> Ulica <form:input path="street"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Miasto <form:input path="city"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Kod pocztowy <form:input path="zipCode"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Numer telefonu <form:input path="phoneNumber"/>
                            </label>
                        </div>
                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label> Data <form:input type="date" path="pickUpDate"/>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Godzina <form:input type="time" path="pickUpTime"/>
                        </div>1

                        <div class="form-group form-group--inline">
                            <label>
                                Uwagi dla kuriera
                                <form:textarea path="pickUpComment"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step" onclick="return summary()" >Dalej</button>
                </div>
            </div>

            <!-- STEP 6 -->
            <div data-step="5">
                <h3>Podsumowanie Twojej darowizny</h3>

                <div class="summary">
                    <div class="form-section">
                        <h4>Oddajesz:</h4>
                        <ul>
                            <li>
                                <span class="icon icon-bag"></span>
                                <span id = "placeForQuantity" class="summary--text"></span>
                            </li>

                            <li>
                                <span class="icon icon-hand"></span>
                                <span class="summary--text" id = "placeForInstitution"></span>
                            </li>
                        </ul>
                    </div>

                    <div class="form-section form-section--columns">
                        <div class="form-section--column">
                            <h4>Adres odbioru:</h4>
                            <ul>
                                <li id="placeForStreet"></li>
                                <li id="placeForCity"></li>
                                <li id="placeForZipCode"></li>
                                <li id="placeForPhoneNumber"></li>
                            </ul>
                        </div>

                        <div class="form-section--column">
                            <h4>Termin odbioru:</h4>
                            <ul>
                                <li id="placeForPickUpDate"></li>
                                <li id="placeForPickUpTime"></li>
                                <li id="placeForPickUpComment"></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="submit" class="btn">Potwierdzam</button>
                </div>
            </div>
        </form:form>
    </div>
</section>

<%@include file="/WEB-INF/footer.jspf"%>

<script src="<c:url value="resources/js/app.js"/>"></script>
<script>
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
</script>

</body>
</html>
