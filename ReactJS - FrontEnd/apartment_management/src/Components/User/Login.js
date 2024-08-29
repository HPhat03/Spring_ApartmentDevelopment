import { Box, Button, Modal, Stack, TextField } from "@mui/material";
import UserStyle from "./UserStyle";
import { useContext, useState } from "react";
import APIs, { authApi, endpoint } from "../../configs/APIs";
import cookie from 'react-cookies'
import { Form } from "react-bootstrap";
import { MyUserDispatchContext } from "../../configs/Context";
import { useNavigate } from "react-router-dom";

const Login = () => {
    const [user, SetUser] = useState({})
    const [modal,SetModal] = useState(false)
    const dispatch = useContext(MyUserDispatchContext);
    const nav = useNavigate()
    const login = async (e) => {
        e.preventDefault();
        console.log(user);
        try {
            const res = await APIs.post(endpoint['login'], {...user});
            if(res.status==200){
                cookie.save("token", res.data, {
                    path: "/"
                })
                const u = await authApi().get(endpoint['current-user']);
                cookie.save("user", u.data, {
                    path: "/"
                });
                
                dispatch({
                    'type': "login",
                    'payload': u.data
                })
                nav('/')
            }
        }
        catch (exc){
            SetModal(true)
            console.log(exc)
        }

        
    }
    return (
        <div style={UserStyle.LoginBackground}>
            <div style={UserStyle.LoginPanel}>
                <h4 style={{padding: 50}}>PNApartment.com</h4>
                <div style={{paddingLeft: 50, paddingTop: 50}}>
                    <div style={{fontSize: 30, fontWeight: "lighter"}}>XIN CHÀO!</div>
                    <h2 style={{color: "#E4A11B", fontWeight: "bolder"}}>CHUNG CƯ PNA</h2>
                    <Form onSubmit={login}>
                    <Stack justifyContent="center" spacing={5} padding={10} style={{...UserStyle.TextBox, width: 500, height: 300, borderRadius: 20}}>
                        <TextField  required id="outlined-required" label="Tên đăng nhập" color="warning"  onChange={(e) => {SetUser({...user, "username": e.target.value})}}/>
                        <TextField  type="password" required id="outlined-required" label="Mật khẩu" color="warning" onChange={(e) => {SetUser({...user, "password": e.target.value})}}/>
                        <Button type="submit" style={{width: 200, marginLeft: "20%"}} variant="contained">Đăng nhập</Button>
                    </Stack>
                    </Form>
                </div>
                <p style={{paddingLeft: 55}}>Nếu chưa có tài khoản, bạn có thể liên hệ Admin để được giúp đỡ.</p>

            </div>
            <Modal open={modal} onClose={() => SetModal(false)} aria-labelledby="child-modal-title" aria-describedby="child-modal-description">
                <Box sx={{ ...UserStyle.modal, width: 600 }}>
                <h2 id="child-modal-title" className="text-danger">Đăng nhập không thành công</h2>
                <p id="child-modal-description">
                    Tên đăng nhập hoặc mật khẩu không chính xác.
                </p>
                <Button  onClick={() => SetModal(false)}>Thử lại</Button>
                </Box>
      </Modal>                 
        </div>
    );
}

export default Login;