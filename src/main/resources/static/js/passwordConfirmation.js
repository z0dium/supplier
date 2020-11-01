var check = function() {
  if (document.getElementById('password').value ==
    document.getElementById('confirm_password').value) {

      document.getElementById('password').className = 'match';
      document.getElementById('confirm_password').className = 'match';

   } else {
      document.getElementById('password').className = 'different';
      document.getElementById('confirm_password').className = 'different';
   }
}