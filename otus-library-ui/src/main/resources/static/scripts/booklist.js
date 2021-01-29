$(document).ready(function() {
    loadingDoc(2, 1);
});

function loadingDoc(PageSize, PageNumber) {
    $.get('/rest/books/?size=' + PageSize + '&page=' + PageNumber).done(function(page) {
        $('tbody').empty();
        page.content.forEach(function(book) {
            $('tbody').append(`
                            <tr>
                                <td><a href="/ui/book/view/${book.BookId}">${book.BookId}</a></td>
                                <td>${book.Name}</td>
                                <td>${book.Category.Name}</td>
                                <td>${book.Authors.length==0?0:book.Authors.length+" authors"}</td>
                                <td>${book.Comments.length==0?0:book.Comments.length+" comments"}</td>
                                <td><a href="/ui/book/delete/${book.BookId}">Удалить</a></td>
                            </tr>
                        `)
        });
        var result = Paging(page.number + 1, page.totalPages, "active", "pagination");
        $("#pagingDiv").html(result);
        $("a", " #pagingDiv").each(function() {
            $(this).attr("href", "#");
            pageNumber = $(this).attr("pn");
            $(this).attr("onclick", "loadingDoc(" + page.size + "," + pageNumber + ")");
        });
    }).fail(function($xhr) {
        var error = $xhr.responseJSON;
        console.log("ERROR : ", error);
        $('#errorId').text(error.message);
    });;
};

function Paging(PageNumber, TotalPages, ClassName, DisableClassName) {
    var ReturnValue = "";
    if (+PageNumber > 1) {
        if (+PageNumber == 2)
            ReturnValue = ReturnValue + "<a pn='" + (+PageNumber - 1) + "' class='" + ClassName + "'>Previous</a>   ";
        else {
            ReturnValue = ReturnValue + "<a pn='";
            ReturnValue = ReturnValue + (+PageNumber - 1) + "' class='" + ClassName + "'>Previous</a>   ";
        }
    } else
        ReturnValue = ReturnValue + "<span class='" + DisableClassName + "'>Previous</span>   ";
    if ((+PageNumber - 3) > 1)
        ReturnValue = ReturnValue + "<a pn='1' class='" + ClassName + "'>1</a> ..... | ";
    for (var i = +PageNumber - 3; i <= +PageNumber; i++)
        if (i >= 1) {
            if (+PageNumber != i) {
                ReturnValue = ReturnValue + "<a pn='";
                ReturnValue = ReturnValue + i + "' class='" + ClassName + "'>" + i + "</a> | ";
            } else {
                ReturnValue = ReturnValue + "<span style='font-weight:bold;'>" + i + "</span> | ";
            }
        }
    for (var i = +PageNumber + 1; i <= +PageNumber + 3; i++)
        if (i <= TotalPages) {
            if (+PageNumber != i) {
                ReturnValue = ReturnValue + "<a pn='";
                ReturnValue = ReturnValue + i + "' class='" + ClassName + "'>" + i + "</a> | ";
            } else {
                ReturnValue = ReturnValue + "<span style='font-weight:bold;'>" + i + "</span> | ";
            }
        }
    if ((+PageNumber + 3) < TotalPages) {
        ReturnValue = ReturnValue + "..... <a pn='";
        ReturnValue = ReturnValue + TotalPages + "' class='" + ClassName + "'>" + TotalPages + "</a>";
    }
    if (+PageNumber < TotalPages) {
        ReturnValue = ReturnValue + "   <a pn='";
        ReturnValue = ReturnValue + (+PageNumber + 1) + "' class='" + ClassName + "'>Next</a>";
    } else
        ReturnValue = ReturnValue + "   <span class='" + DisableClassName + "'>Next</span>";
    return (ReturnValue);
}