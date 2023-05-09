function validateForm() {
const form = document.querySelector('form');
const emailInput = document.querySelector('input[name="email"]');
const passwordInput = document.querySelector('input[name="password"]');

form.addEventListener('submit', (event) => {
  //event.preventDefault();

  const email = emailInput.value.trim();
  const password = passwordInput.value.trim();

  if (!email || !password) {
    alert('Please enter both email and password');
    return;
  }


  // Use AJAX or Fetch to send login request to server and handle response here
  // ...
});
}