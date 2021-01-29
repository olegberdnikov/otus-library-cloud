function loadingDoc(BookId) {
    $(document).ready(function() {
        $.get('/rest/book/' + BookId).done(function(book) {
                $('#id').text(book.BookId);
                $('#nameId').text(book.Name);
                $('#categoryIds').text(book.Category.Name);
                var html = "";
                $.each(book.Authors, function(index1, author) {
                    html += " <li>" + author.Name + " " + author.Surname + "</li> ";
                });
                $('#authorsIds').html(html);
                book.Comments.forEach(function(comment) {
                    $('#tbodyComments').append(`
                                        <tr>
                                            <td>${comment.Created}</td>
                                            <td>${comment.Text}</td>
                                        </tr>
                                    `)
                });
            })
            .fail(function($xhr) {
                var error = $xhr.responseJSON;
                console.log("ERROR : ", error);
                $('#errorId').text(error.message);
            });;
    });
};

function deleteBook(BookId) {
    $.ajax({
        url: '/rest/book/' + BookId,
        type: 'DELETE',
        success: function(data) {
            window.location = "/ui/book/booklist";
        },
        error: function($xhr, textStatus, errorThrown) {
            var error = $xhr.responseJSON;
            console.log("ERROR : ", error);
            $('#errorId').text(error.message);
        }
    });
};