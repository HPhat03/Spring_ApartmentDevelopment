import Stack from "@mui/material/Stack"
import ApartmentHeader from "../Common/ApartmentHeader"
import CommonStyle from "../Common/Style"
import UserStyle from "../User/UserStyle"
import { useContext, useEffect, useRef, useState } from "react"
import { MyUserContext } from "../../configs/Context"
import { useNavigate, useParams, useSearchParams } from "react-router-dom"
import { authApi, endpoint } from "../../configs/APIs"

import Modal from "@mui/material/Modal"
import Box from '@mui/material/Box'
import Button from 'react-bootstrap/Button';
import Form from "react-bootstrap/Form"
import TextField from "@mui/material/TextField"
import { responsive } from "../smart cabinet/SmartCabinet"
import Carousel from "react-multi-carousel"

const ReportReg = () => {
    const user = useContext(MyUserContext)
    const { id } = useParams()
    const nav = useNavigate()


    const [addPic, setAddPic] = useState(false)
    const [modal, SetModal] = useState(false)
    const [error, setError] = useState("")
    const [navable, SetNavable] = useState(true)
    const [report, setReport] = useState(null)

    const [reload, setReload] = useState(true)
    const [current, setCurrent] = useState(null)
    const pictures = useRef()

    useEffect(() => {
        setReport({
            apartmentId: parseInt(id),
            details: [
                {
                    content: "",
                    pictures: []
                }
            ]
        })
        if (user === null)
            nav('/login')
    }, [id, reload])

    const setDetails = (index, data) => {
        const details = report.details;
        for (let i = 0; i < details.length; i++) {
            if (i === index) {
                details[i].content = data.content === undefined || data.content === null ? details[i].content : data.content;
                details[i].pictures = data.pictures === undefined || data.pictures === null ? details[i].pictures : data.pictures;
            }
        }

        setReport({ ...report, details: details });
    }

    const submit = async (e) => {
        e.preventDefault()

        console.log(report)
        const res = await authApi().post(endpoint['create_report'], report)
        if(res.status === 200)
        {
            setError(res.data)
            SetModal(true)
            SetNavable(false)
        }else if(res.status===201)
        {
            nav(`/apartments/${id}/reports/?page=1&created=true`)
            setReload(!reload)
        }
    }

    const setPictures = async (e) => {
        e.preventDefault()
        if(current === null ) return
        console.log(pictures.current.files)
        const form = new FormData()
        const array = pictures.current.files
        for(let i =0; i<array.length; i++)
            form.append("files", array[i]);
        const res = await authApi().post(endpoint['upload'], form, {
            headers:
            {
                "Content-Type": "multipart/form-data"
            }
        })
        setDetails(current, {pictures: res.data})
        setAddPic(false)
    }
    return (
        <div>
            <ApartmentHeader />
            <div style={{ ...CommonStyle.flex_center, padding: "0px 100px", width: "100%", marginTop: 40 }}>\
                <div style={{ ...CommonStyle.contentBlock, ...CommonStyle.Box, marginLeft: 10, width: "90%" }}>
                    <Form onSubmit={submit}>
                        <h4 style={{textAlign: "center"}}>BÁO CÁO SỰ CỐ</h4>
                        <TextField required id="outlined-required" className="mt-3" style={{width: "100%" }} label="Tiêu đề" color="warning" onChange={(e) => { setReport({ ...report, "title": e.target.value }) }} />
                        {report === null ? <></> : report.details.map((x, i) => <div className="m-2">
                            <h4 className="text-danger m-3">Sự cố {i+1}:</h4>
                            <div style={{...CommonStyle.flex_space, marginBottom: 20}}>
                            <TextField required id="outlined-required" label="Nội dung" style={{width: "50%" }}value={x.content} color="warning" onChange={(e) => setDetails(i, { content: e.target.value })} />
                            <Button className="btn-danger" onClick={() => {setCurrent(i); setAddPic(true)}}>Thêm ảnh minh chứng</Button>  
                            </div>
                            
                            <Carousel responsive={responsive} className="bg-secondary rounded">
                                {x.pictures.map((x) => <div style={{backgroundColor: "white", height: 300, width: 200, margin: "10px 70px", ...CommonStyle.Box, backgroundImage: `url(${x})`, backgroundSize: "100%"}}></div>)}
                            </Carousel>

                        </div>)}
                        <Button className="m-4 btn-warning"onClick={() => { 
                            setReport({ ...report, 
                            details: [...report.details, { content: "", pictures: [] }] }) }} >Thêm nội dung báo cáo</Button>
                            <br/>
                        <Button className="m-4 mt-2 btn-success" type="submit" >Gửi báo cáo</Button>
                    </Form>
                </div>
            </div >

            <Modal open={modal} onClose={() => { SetModal(false); if(navable){ nav('/'); }}} aria-labelledby="child-modal-title" aria-describedby="child-modal-description">
                <Box sx={{ ...UserStyle.modal, width: 600 }}>
                    <h2 id="child-modal-title" className="text-danger">Thông báo lỗi</h2>
                    <p id="child-modal-description">
                        {error}
                    </p>
                    <Button onClick={() => { SetModal(false); if(navable){ nav('/');} }}>Đóng</Button>
                </Box>
            </Modal>

            <Modal open={addPic} aria-labelledby="child-modal-title" aria-describedby="child-modal-description">
                <Box sx={{ ...CommonStyle.modal, width: 600 }}>
                    <h2 id="child-modal-title">Thêm ảnh minh chứng</h2>
                    <Form onSubmit={setPictures}>
                        <Form.Group className="mb-2" controlId="avatar">
                            <Form.Label>(chấp nhận loại ảnh .png/.jpg/.jpge)</Form.Label>
                            <Form.Control type="file" accept=".png,.jpg,.jpge" multiple ref={pictures} />
                            <div style={{...CommonStyle.flex_space}}>                            
                                <Button type="submit" className="m-3 btn-success" value="primary">Thêm</Button>
                                <Button onClick={() => setAddPic(false)} className="m-3 btn-danger" value="primary">Đóng</Button>
                            </div>

                        </Form.Group>
                        
                    </Form>
                </Box>
            </Modal>
        </div >
    )
}

export default ReportReg