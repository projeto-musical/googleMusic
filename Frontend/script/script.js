  function openTab(event, tabId) {
            document.querySelectorAll('.tab-content').forEach(c => c.classList.remove('active-content'));
            document.querySelectorAll('.tab-btn').forEach(b => b.classList.remove('active'));

            document.getElementById(tabId).classList.add('active-content');
            event.currentTarget.classList.add('active');
        }

        function toggleTheme() {
            const body = document.body;
            const icon = document.querySelector('#theme-toggle i');
            body.classList.toggle('dark-theme');
            
            if (body.classList.contains('dark-theme')) {
                icon.className = 'fa-solid fa-sun';
                localStorage.setItem('theme', 'dark');
            } else {
                icon.className = 'fa-solid fa-moon';
                localStorage.setItem('theme', 'light');
            }
        }

        function resetForm(formId) {
            document.getElementById(formId).reset();
        }

        window.addEventListener('DOMContentLoaded', () => {
            if (localStorage.getItem('theme') === 'dark') {
                document.body.classList.add('dark-theme');
                document.querySelector('#theme-toggle i').className = 'fa-solid fa-sun';
            }
        });

        // CONEXÃO COM A API :

        