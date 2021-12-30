//window.onload = function () {} script를 위에 적으려면 이거 사용해야함
    const btnContainerElem = document.querySelector('#btnContainer');
    const btnDelElem = btnContainerElem.querySelector('#btnDel');
    btnDelElem.addEventListener('click', function () {
        if (confirm('삭제하시겠습니까?')) {
            location.href = '/board/del?iboard=' + btnContainerElem.dataset.iboard;
        }
    });
