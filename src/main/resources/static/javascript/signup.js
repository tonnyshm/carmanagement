const signupForm = document.getElementById('signup-form');
const signupButton = document.getElementById('signup-button');
const backToLoginButton = document.getElementById('back-to-login-button');

//// Submit the form when the "Sign Up" button is clicked
//signupButton.addEventListener('click', (event) => {
//  event.preventDefault();
//  signupForm.submit();
//});

// Return to the login page when the "Back to Login" button is clicked
backToLoginButton.addEventListener('click', () => {
  window.location.href = 'login.html';
});

// Get form elements
const signupForm = document.getElementById('signup-form');
const firstNameInput = document.getElementById('first-name');
const lastNameInput = document.getElementById('last-name');
const emailInput = document.getElementById('email');
const passwordInput = document.getElementById('password');
const phoneNumberInput = document.getElementById('phone-number');
const addressInput = document.getElementById('address');
const imageInput = document.getElementById('image');
const signupButton = document.getElementById('signup-button');

// Add event listener to signup button
signupButton.addEventListener('click', (event) => {
  event.preventDefault();

  // Validate first name
  if (firstNameInput.value.trim() === '') {
    alert('Please enter your first name');
    firstNameInput.focus();
    return;
  }

  // Validate last name
  if (lastNameInput.value.trim() === '') {
    alert('Please enter your last name');
    lastNameInput.focus();
    return;
  }

  // Validate email
  if (emailInput.value.trim() === '') {
    alert('Please enter your email address');
    emailInput.focus();
    return;
  } else if (!isValidEmail(emailInput.value.trim())) {
    alert('Please enter a valid email address');
    emailInput.focus();
    return;
  }

  // Validate password
  if (passwordInput.value.trim() === '') {
    alert('Please enter your password');
    passwordInput.focus();
    return;
  } else if (passwordInput.value.trim().length < 8) {
    alert('Password must be at least 8 characters long');
    passwordInput.focus();
    return;
  }

  // Validate phone number
  if (phoneNumberInput.value.trim() === '') {
    alert('Please enter your phone number');
    phoneNumberInput.focus();
    return;
  } else if (!isValidPhoneNumber(phoneNumberInput.value.trim())) {
    alert('Please enter a valid phone number');
    phoneNumberInput.focus();
    return;
  }

  // Validate address
  if (addressInput.value.trim() === '') {
    alert('Please enter your address');
    addressInput.focus();
    return;
  }

  // Validate image upload
  if (imageInput.files.length > 0) {
    const fileType = imageInput.files[0].type;
    if (!(fileType === 'image/jpeg' || fileType === 'image/png' || fileType === 'application/pdf')) {
      alert('Please upload a valid image or PDF file');
      imageInput.focus();
      return;
    }
  }

  // If all input is valid, submit the form
  signupForm.submit();
});

// Validate email format
function isValidEmail(email) {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailRegex.test(email);
}

// Validate phone number format
function isValidPhoneNumber(phoneNumber) {
  const phoneNumberRegex = /^\d{10}$/;
  return phoneNumberRegex.test(phoneNumber);
}

