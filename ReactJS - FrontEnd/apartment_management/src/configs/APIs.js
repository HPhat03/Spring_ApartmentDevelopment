import axios from "axios"
import cookie from "react-cookies"
const BASE_URL = "http://localhost:8080/ApartmentManagement/"

export const endpoint = {
    'login': "/api/login/",
    'current-user': "/api/me/",
    'change-user': (id) => `/api/user/${id}/`,
    'change-user-avatar': (id) => `/api/user/${id}/add_avatar/`,
    'my_apartment': "/api/my_constract/",
    'detail_apartment': (id) => `/api/constract/${id}/`,
    'receipts': (id) => `/api/constract/${id}/receipts/`,
    'detail_receipts': (id) => `/api/receipt/${id}/`,
    'relative_registry': (id) => `/api/constract/${id}/relative_registry/`,
    'relative_Register': '/api/relative_registries/',
    'cabinets': (id) => `/api/constract/${id}/smart_cabinets/`,
    'reports': (id) => `/api/constract/${id}/reports/`,
    'create_report': '/api/reports/',
    'upload': '/api/upload/',
    'detail_report': (id) => `/api/reports/${id}/`,
    'surveys': (page) => `/api/survey_request/?page=${page}`,
    'survey_questions': (id) => `/api/survey_request/${id}/details/`,
    'survey_response': '/api/survey_response/',
    'vnpay': '/api/vnpay/create_payment/',
    'validate_payment': '/api/validate_transaction/'
}
export const authApi = () => {
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            'Authorization': cookie.load('token')
        }
    })
}
export default axios.create({
        baseURL: BASE_URL
    })