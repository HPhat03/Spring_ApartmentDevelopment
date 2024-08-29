import { useContext, useEffect, useRef, useState } from "react"
import { CurrentApartmentDispatcherContext, MyApartmentContext, MyApartmentDispatchContext, MyUserContext, MyUserDispatchContext } from "../../configs/Context"
import { useNavigate } from "react-router-dom";
import CommonStyle from "./Style";
import { authApi, endpoint } from "../../configs/APIs";
import cookie from "react-cookies"
import { Button, Form, Spinner } from "react-bootstrap";
import { Box, Modal, Stack, TextField } from "@mui/material";
import UserStyle from "../User/UserStyle";

const LoginedHome = () => {
    const user = useContext(MyUserContext);
    const nav = useNavigate();
    const dispatch = useContext(MyUserDispatchContext);
    const dispatchCr = useContext(CurrentApartmentDispatcherContext)
    const [oPass, setPass] = useState(false)
    const [oPic, setPic] = useState(false)
    const [surveys, setSurveys] = useState(null)
    const [password, setPw] = useState("")
    const [confirm, setConfirm] = useState("")

    const [reload, setReload] = useState(true)
    const [page, setPage] = useState(1)

    const avatar = useRef();
    const apartments = useContext(MyApartmentContext)
    const setApartments = useContext(MyApartmentDispatchContext)
    const formatter = new Intl.NumberFormat('vn', {
        style: 'currency',
        currency: 'VND',
    })

    useEffect(() => {
        fetchData()
    },[])
    useEffect(() => {
        fetchSurvey()
    }, [reload])

    const fetchData = async () => {
        if (user===null)
            nav("/login/")
        else if(user.first_login==1)
            setPass(true)

        const myAP = await authApi().get(endpoint['my_apartment']);
        
        setApartments({type: "save", payload: myAP.data});
        cookie.save("apartments", myAP.data, {path: '/'})
        // console.log(apartments)
    }

    const fetchSurvey = async () => {
        const res = await authApi().get(endpoint['surveys'](page))
        setSurveys(res.data)
    }

    const changePass = async (e) => {
        e.preventDefault();

        if(password !== confirm )
        {
                alert("Mật khẩu không trùng khớp, vui lòng thử lại")
        }else{
            const res = await authApi().patch(endpoint["change-user"](user.id), {
                "password": password
            })
            console.log(res.data)
            if(res===200)
            {
                cookie.remove("user")
                cookie.save("user", res.data, {
                    path: "/"
                });
            
            dispatch({
                'type': "login",
                'payload': res.data
            })
            
            }
            setPass(false)
            setPic(true)
        }
    }

    const changePic = async (e) => {
        e.preventDefault();

        const form = new FormData()
        form.append("file", avatar.current.files[0])
        const res = await authApi().post(endpoint["change-user-avatar"](user.id), form, {
            headers:
            {
                "Content-Type": "multipart/form-data"
            }
        })
        if(res.status===200)
        {
            cookie.remove("user")
            cookie.save("user", res.data, {
                path: "/"
            });
            
            dispatch({
                'type': "login",
                'payload': res.data
            })
        }
        
        setPic(false)
    }

    const navToDetail = (e, id, roomNumber) => {
        e.preventDefault()
        const payload = {roomNumber: roomNumber, id: id}
        dispatchCr({type: "change", payload: payload })
        cookie.save("current", payload, {path: "/"})
        nav(`/apartments/${id}/`)
    }
    const next = () => {
        setPage(page + 1)
        setReload(!reload)
    }
    const pre = () => {
        setPage(page - 1)
        setReload(!reload)
    }
    const checkDate = (str) => {
        var arr = str.split('/');
        var endDate = new Date(parseInt(arr[2]), parseInt(arr[1])-1, parseInt(arr[0]))
        return new Date() < endDate;
    }
    return (
        (user==null) ? <></> : 
        <div>
            <div style={{...CommonStyle.flex_space, position: "relative", width: "100%", height: 500, backgroundImage: "url(https://cdn.getmidnight.com/113180fa10fcf7a118ecdbcd21c4cd24/2021/09/benefits-of-remote-work-og-1.jpg)", backgroundPositionY: -200, backgroundSize: "100%"}}>
                <div style={{color: "white", backgroundColor: "rgba(0, 28, 69, 0.6)", padding: 20, width: "50%"}}>
                    <h1 style={{fontSize: 60}}>PNApartment.com</h1>
                    <div>Hãy bắt đầu quản lý một căn chung cư PNA</div>
                </div>
            </div>
            <div style={{...CommonStyle.Component, height: apartments==null ? 200 : Math.ceil(apartments.length/3)*450 }}>
                <div style={{margin: 20}}>
                    <h4>YOUR APARTMENT</h4>
                    <div>Chung cư của bạn</div>
                    
                </div>
                <Stack flexWrap="wrap" direction="row" justifyContent={apartments==null ? "center" : "left"} alignItems="center">
                    {apartments == null ? <Spinner animation="grow" variant="primary" /> : 
                    apartments.map((x, i) => {
                        return (
                        <div key={x.id} onClick={(e) => {navToDetail(e, x.id, x.room.roomNumber)}} style={{...CommonStyle.flex_space, ...CommonStyle.Box, flexDirection: "column", padding: 10, margin: "20px 30px"}}>
                            <div style={{...CommonStyle.flex_space, backgroundImage: "url(https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSnMBJCWshZy4sNKZOQm91aT0c6R0S8_2h-0g&s)", width: 300, padding: 10, height: 180}}>
                                <div style={{...CommonStyle.flex_space, width: "100%", padding: 5, backgroundColor: "rgba(0, 28, 69, 0.6)", color: "white", marginTop: 130}}>
                                    <div style={{backgroundColor: "green", borderRadius: 10, padding: 5}}>Thuê</div>
                                    <div style={{}}>Chung cư</div>
                                    <div style={{}}>{formatter.format(x.finalPrice)}</div>
                                </div>
                            </div>
                            <div style={{...CommonStyle.flex_center, flexDirection: "column", height: 100}}>
                                <div style={{fontSize: 12, color: "#A86902"}}>Tầng {x.room.roomNumber.charAt(0)}</div>
                                <div style={{fontSize: 18, fontWeight: "bold"}}>Phòng {x.room.roomNumber}</div>
                            </div>
                            <div style={{...CommonStyle.flex_space, ...CommonStyle.Box, height: 30, width: "100%", padding: 5, paddingLeft: 10, paddingRight: 10}}>
                                <div>{i}</div>
                                <div>Từ ngày {x.createdDate}</div>
                            </div>
                        </div>
                        )})}
                </Stack>
            </div>

            <div style={{...CommonStyle.Component, height: 550, marginBottom: 100 , marginTop: 100}}>
                <div style={{margin: 20}}>
                    <h4>SURVEYS</h4>
                    <div>Thực hiện các khảo sát định kì giúp chúng tôi cải thiện chất lượng cuộc sống của bạn</div>
                </div>
                <div style={{ ...CommonStyle.contentBlock, ...CommonStyle.Box, margin: "10px 50px", width: "90%" }}>
                    {surveys === null ? <div style={{ ...CommonStyle.flex_center, height: "100%" }}><Spinner animation="grow" variant="primary" /></div> : surveys.map((x) => {
                        return <div key={x.id} style={{ ...CommonStyle.flex_space, ...CommonStyle.Box, padding: 15, marginBottom: 10 }}>
                            <div>
                                <h4>{x.name}</h4>
                                <div>Bắt đầu từ ngày <strong>{x.startDate}</strong> đến hết ngày <strong>{x.endDate}</strong></div>
                            </div>
                            <div>{x.isActive===1 && checkDate(x.endDate) ? <div className="text-success">ĐANG DIỄN RA</div> : <div className="text-danger">ĐÃ KẾT THÚC</div>}</div>
                            <Button onClick={() => nav(`/surveys/${x.id}/new_response/`)} disabled={x.isActive===1 && checkDate(x.endDate) ? false : true}>Thêm phiếu trả lời</Button>
                        </div>
                    })}
                    {surveys == null ? <></> :
                        <div style={{ ...CommonStyle.flex_space }}>
                            <Button onClick={pre} disabled={page === 1 ? true : false} variant="outline-dark">Quay lại</Button>
                            <Button onClick={next} disabled={surveys.length < 4 || surveys == []} variant="outline-dark">Tiếp theo</Button>
                        </div>
                    }

                </div>
            </div>

            <Modal open={oPass} aria-labelledby="child-modal-title" aria-describedby="child-modal-description">
                <Box sx={{ ...CommonStyle.modal, width: 600, borderRadius: 10}}>
                <h2 id="child-modal-title" className="text">Thay đổi mật khẩu</h2>
                <Form onSubmit={changePass}>
                    <Stack justifyContent="center" spacing={5} padding={10} style={{...UserStyle.TextBox, width: 500, height: 300, borderRadius: 20}}>
                        <TextField  type="password" required id="password" label="Mật khẩu mới" color="warning" value={password} onChange={(e) => {setPw(e.target.value)}}/>
                        <TextField  type="password" required id="confirm" label="Nhập lại mật khẩu" color="warning" value={confirm} onChange={(e) => {setConfirm(e.target.value)}}/>
                        <Button type="submit" className="btn btn-success" style={{width: 200, marginLeft: "20%"}} variant="contained" color="warning">Thay đổi</Button>
                    </Stack>
                </Form>
                </Box>
            </Modal>
            <Modal open={oPic} aria-labelledby="child-modal-title" aria-describedby="child-modal-description">
                <Box sx={{ ...CommonStyle.modal, width: 600 }}>
                <h2 id="child-modal-title">Thay đổi ảnh đại diện</h2>
                <Form onSubmit={changePic}>
                    <Form.Group className="mb-2" controlId="avatar">
                        <Form.Label>Ảnh đại diện cần rõ mặt của quý khách</Form.Label>
                        <Form.Control type="file" accept=".png,.jpg" ref={avatar}/>
                        <Button type="submit" value="primary">Thay đổi</Button>
                    </Form.Group>
                </Form>
                </Box>
            </Modal>    
        </div>
        
    )
}

export default LoginedHome