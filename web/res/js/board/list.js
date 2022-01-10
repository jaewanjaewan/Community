{
   const recordList = document.querySelectorAll('.record');
   recordList.forEach((item) => {
      item.addEventListener('click', (e) => {
         const iboard = item.dataset.iboard;
         location.href = '/board/detail?iboard='+iboard;
      })
   })
}
