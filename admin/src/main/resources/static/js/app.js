// 应用主要JavaScript功能
document.addEventListener('DOMContentLoaded', function() {
    // 初始化提示框
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    const tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });

    // 自动隐藏警告消息
    setTimeout(function() {
        const alerts = document.querySelectorAll('.alert');
        alerts.forEach(function(alert) {
            const bsAlert = new bootstrap.Alert(alert);
            bsAlert.close();
        });
    }, 5000);
});

// HTMX 全局配置
document.body.addEventListener('htmx:configRequest', function(evt) {
    // 添加CSRF令牌（如果需要）
    const token = document.querySelector('meta[name="_csrf"]');
    const header = document.querySelector('meta[name="_csrf_header"]');
    if (token && header) {
        evt.detail.headers[header.getAttribute('content')] = token.getAttribute('content');
    }
});

// HTMX 错误处理
document.body.addEventListener('htmx:responseError', function(evt) {
    console.error('HTMX Error:', evt.detail);
    alert('请求失败，请稍后重试');
});

// 搜索防抖
function debounce(func, wait) {
    let timeout;
    return function executedFunction(...args) {
        const later = () => {
            clearTimeout(timeout);
            func(...args);
        };
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
    };
}

// 表单验证增强
function validateForm(form) {
    let isValid = true;
    const inputs = form.querySelectorAll('input[required], select[required], textarea[required]');

    inputs.forEach(function(input) {
        if (!input.value.trim()) {
            input.classList.add('is-invalid');
            isValid = false;
        } else {
            input.classList.remove('is-invalid');
        }
    });

    return isValid;
}

// 确认对话框
function confirmAction(message, callback) {
    if (confirm(message)) {
        callback();
    }
}

// 页面加载指示器
document.body.addEventListener('htmx:beforeRequest', function(evt) {
    document.body.style.cursor = 'wait';
});

document.body.addEventListener('htmx:afterRequest', function(evt) {
    document.body.style.cursor = 'default';
});