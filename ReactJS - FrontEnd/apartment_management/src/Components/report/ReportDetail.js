
import ApartmentHeader from "../Common/ApartmentHeader"
import CommonStyle from "../Common/Style"
import UserStyle from "../User/UserStyle"
import { useContext, useEffect, useState } from "react"
import { MyUserContext } from "../../configs/Context"
import { useNavigate, useParams } from "react-router-dom"
import { authApi, endpoint } from "../../configs/APIs"

import Modal from "@mui/material/Modal"
import Box from '@mui/material/Box'
import Button from 'react-bootstrap/Button';
import Form from "react-bootstrap/Form"
import TextField from "@mui/material/TextField"
import { responsive } from "../smart cabinet/SmartCabinet"
import Carousel from "react-multi-carousel"
import Spinner from "react-bootstrap/Spinner"

const ReportDetail = () => {
    const user = useContext(MyUserContext)
    const { id } = useParams()
    const nav = useNavigate()


    const [modal, SetModal] = useState(false)
    const [error, setError] = useState("")
    const [report, setReport] = useState(null)

    useEffect(() => {
        if (user === null)
            nav('/login')
        else
            fetchData()
    }, [id])

    const fetchData = async () => {
        const res = await authApi().get(endpoint['detail_report'](id))
        console.log(res.data)
        if(typeof res.data === "string"){
            setError(res.data)
            SetModal(true)
        }
        else{
            setReport(res.data)
        }
    }

    
    return (
        <div>
            <ApartmentHeader />
            {report===null ? <div style={{ ...CommonStyle.flex_center, height: "100%" }}><Spinner animation="grow" variant="primary" /></div> :
            <div style={{ ...CommonStyle.flex_center, padding: "0px 100px", width: "100%", marginTop: 40 }}>\
                <div style={{ ...CommonStyle.contentBlock, ...CommonStyle.Box, marginLeft: 10, width: "90%" }}>
                    <Form>
                        <h4 style={{textAlign: "center"}}>BÁO CÁO SỰ CỐ</h4>
                        <div>Mã sự cố: <strong>{report===null ? 9999 : report.id}</strong></div>
                        <TextField disabled id="outlined-required" className="mt-3" style={{width: "100%" }} value={report.title} color="warning" />
                        {report === null ? <></> : report.details.map((x, i) => <div className="m-2">
                            <h4 className="text-danger m-3">Sự cố {i+1}:</h4>
                            <div style={{...CommonStyle.flex_space, marginBottom: 20}}>
                            <TextField disabled id="outlined-required" label="Nội dung" style={{width: "100%" }} value={x.content} color="warning" />
                            </div>
                            
                            <Carousel responsive={responsive} className="bg-secondary rounded">
                                {x.pictures.map((x) => <div style={{backgroundColor: "white", height: 300, width: 200, margin: "10px 70px", ...CommonStyle.Box, backgroundImage: `url(${x})`, backgroundSize: "100%"}}></div>)}
                            </Carousel>

                        </div>)}
                    </Form>
                </div>
            </div >
            }
            <Modal open={modal} onClose={() => { SetModal(false); nav('/'); }} aria-labelledby="child-modal-title" aria-describedby="child-modal-description">
                <Box sx={{ ...UserStyle.modal, width: 600 }}>
                    <h2 id="child-modal-title" className="text-danger">Thông báo lỗi</h2>
                    <p id="child-modal-description">
                        {error}
                    </p>
                    <Button onClick={() => { SetModal(false);  nav('/'); }}>Đóng</Button>
                </Box>
            </Modal>
        </div >

    )
}

export default ReportDetail