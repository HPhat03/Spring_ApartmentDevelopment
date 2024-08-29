import cookie from "react-cookies"
const MyUserReducer = (current, action) => {
    switch (action.type){
        case "login":
            return action.payload;
        case "logout":{
            cookie.remove("token")
            cookie.remove('user')
            return null
        }
    }
    return current;
}
export const ChatToggleReducer = (current, action) => {
    switch (action.type){
        case "on":
            return true;
        case "off":
            return false;
        case "toggle":
            return !current;
    }
    return current;
}

export const MyApartmentReducer = (current, action) => {
    switch (action.type){
        case "save":
            return action.payload;
    }
    return current;
}

export const CurrentAppartmentReducer = (current, action) => {
    switch (action.type){
        case "change":
            return action.payload;
        case "remove":
            return null;
    }
    return current;
}

export default MyUserReducer