function loadingDoc(BookId) {
    $(document).ready(function() {
        $.get('/rest/book/' + BookId).done(function(book) {
                $('#id').html(book.BookId);
                $('#bookNameId').val(book.Name);
                $('#categoryId option[value='+book.Category.CategoryId+']').prop('selected', true);
                $.each(book.Authors, function(index1, author) {
                   $('#authorIds option[value='+author.AuthorId+']').prop('selected', true);
                });
            })
            .fail(function($xhr) {
                var error = $xhr.responseJSON;
                console.log("ERROR : ", error);
                $('#errorId').text(error.message);
            });;
    });
};

function addBook() {
    var authors = "";
    $.each($("#authorIds option:selected"), function(index1, author) {
        if (authors.length > 0) {
            authors += ","
        }
        authors += "{\"AuthorId\":" + author.value + "}";
    });
    console.log("authors: " + authors);

    var body = "{\"Name\":\"" + $('#bookNameId').val() + "\", \"Category\": {\"CategoryId\":" + $("#categoryId option:selected").val() + "}, \"Authors\": [" + authors + "]}";
    console.log("body: " + body);
    $.ajax({
        url: "/rest/book/",
        type: "POST",
        data: body,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function($xhr, textStatus, errorThrown) {
            window.location = "/ui/book/view/" + $xhr;
        },
        error: function($xhr, textStatus, errorThrown) {
            var error = $xhr.responseJSON;
            console.log("ERROR : ", error);
            $('#errorId').text(error.message);
        }
    });
};

function editBook() {
    var authors = "";
    $.each($("#authorIds option:selected"), function(index1, author) {
        if (authors.length > 0) {
            authors += ","
        }
        authors += "{\"AuthorId\":" + author.value + "}";
    });
    console.log("authors: " + authors);
    var body = "{\"BookId\":"+$('#id').html()+",\"Name\":\"" + $('#bookNameId').val() + "\", \"Category\": {\"CategoryId\":" + $("#categoryId option:selected").val() + "}, \"Authors\": [" + authors + "]}";
    console.log("body: " + body);
    $.ajax({
        url: "/rest/book/",
        type: "PUT",
        data: body,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function($xhr, textStatus, errorThrown) {
            window.location = "/ui/book/view/" + $xhr;
        },
        error: function($xhr, textStatus, errorThrown) {
            var error = $xhr.responseJSON;
            console.log("ERROR : ", error);
            $('#errorId').text(error.message);
        }
    });
};