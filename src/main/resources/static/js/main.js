(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner();

    // Initiate the wowjs
    new WOW().init();

    // Sticky Navbar
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.sticky-top').css('top', '0px');
        } else {
            $('.sticky-top').css('top', '-100px');
        }
    });

    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({ scrollTop: 0 }, 1500, 'easeInOutExpo');
        return false;
    });

    // Header carousel
    $(".header-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1500,
        items: 1,
        dots: true,
        loop: true,
        nav: true,
        navText: [
            '<i class="bi bi-chevron-left"></i>',
            '<i class="bi bi-chevron-right"></i>'
        ]
    });

    // Testimonials carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1000,
        center: true,
        margin: 24,
        dots: true,
        loop: true,
        nav: false,
        responsive: {
            0: {
                items: 1
            },
            768: {
                items: 2
            },
            992: {
                items: 3
            }
        }
    });

})(jQuery);

document.addEventListener('DOMContentLoaded', function () {
    // Login Form Handler
    var loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', function (e) {
            e.preventDefault();
            var email = document.getElementById('email').value;
            var password = document.getElementById('password').value;
            var alertBox = document.getElementById('loginAlert');

            fetch('/api/auth/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email: email, password: password })
            })
                .then(function (response) {
                    if (!response.ok) throw new Error('Invalid credentials');
                    return response.json();
                })
                .then(function (data) {
                    if (data.requireOtp) {
                        document.getElementById('otpEmail').value = data.email;
                        var myModal = new bootstrap.Modal(document.getElementById('otpModal'));
                        myModal.show();
                    } else {
                        localStorage.setItem('user', JSON.stringify(data));
                        window.location.href = 'home.html';
                    }
                })
                .catch(function (error) {
                    alertBox.textContent = error.message;
                    alertBox.classList.remove('d-none');
                });
        });
    }

    // OTP Form Handler
    var otpForm = document.getElementById('otpForm');
    if (otpForm) {
        otpForm.addEventListener('submit', function (e) {
            e.preventDefault();
            var email = document.getElementById('otpEmail').value;
            var otp = document.getElementById('otpInput').value;
            var alertBox = document.getElementById('otpAlert');
            var verifyBtn = document.getElementById('verifyOtpBtn');
            var originalText = verifyBtn.innerText;

            verifyBtn.innerText = "Verifying...";
            verifyBtn.disabled = true;

            fetch('/api/auth/verify-otp', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email: email, otp: otp })
            })
                .then(function (response) {
                    if (!response.ok) throw new Error('Invalid or expired OTP');
                    return response.json();
                })
                .then(function (data) {
                    localStorage.setItem('user', JSON.stringify(data));
                    window.location.href = 'home.html';
                })
                .catch(function (error) {
                    alertBox.textContent = error.message;
                    alertBox.classList.remove('d-none');
                })
                .finally(function () {
                    verifyBtn.innerText = originalText;
                    verifyBtn.disabled = false;
                });
        });
    }

    // Signup Form Handler
    var signupForm = document.getElementById('signupForm');
    if (signupForm) {
        signupForm.addEventListener('submit', function (e) {
            e.preventDefault();
            var fullName = document.getElementById('fullName').value;
            var email = document.getElementById('email').value;
            var password = document.getElementById('password').value;
            var alertBox = document.getElementById('signupAlert');

            fetch('/api/auth/signup', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ fullName: fullName, email: email, password: password })
            })
                .then(function (response) {
                    return response.json().then(function (data) {
                        if (!response.ok) throw new Error(data.error || 'Signup failed');
                        return data;
                    });
                })
                .then(function (data) {
                    alert('Account created successfully! Please log in.');
                    window.location.href = 'index.html';
                })
                .catch(function (error) {
                    alertBox.textContent = error.message;
                    alertBox.classList.remove('d-none');
                });
        });
    }

    // Dynamic Navigation based on Auth State
    var userJson = localStorage.getItem('user');
    var user = userJson ? JSON.parse(userJson) : null;

    // If not on login/signup page and user is not logged in, redirect to login
    var isAuthPage = window.location.pathname.endsWith('index.html') ||
        window.location.pathname.endsWith('signup.html') ||
        window.location.pathname === '/';

    if (!user && !isAuthPage) {
        window.location.href = 'index.html';
    }

    // If user is already logged in, they shouldn't be on the login page!
    if (user && isAuthPage) {
        window.location.href = 'home.html';
    }

    // Update navbar if user is logged in
    if (user && document.querySelector('#navbarCollapse')) {
        var nameDisplay = document.getElementById('userNameDisplay');
        if (nameDisplay) {
            var firstName = user.fullName ? user.fullName.split(" ")[0] : "User";
            nameDisplay.innerHTML = "Welcome, " + firstName;
        }

        var logoutBtn = document.createElement('a');
        logoutBtn.href = "#";
        logoutBtn.className = "btn btn-primary rounded-0 py-4 px-lg-5 d-none d-lg-block";
        logoutBtn.innerHTML = "Logout <i class=\"fa fa-sign-out-alt ms-3\"></i>";
        logoutBtn.onclick = function (e) {
            e.preventDefault();
            localStorage.removeItem('user');
            window.location.href = 'index.html';
        };

        var lastBtn = document.querySelector('#navbarCollapse > a.btn');
        if (lastBtn) {
            lastBtn.replaceWith(logoutBtn);
        }
    }
});
