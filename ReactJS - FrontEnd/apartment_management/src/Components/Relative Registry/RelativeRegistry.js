import Stack from "@mui/material/Stack"
import ApartmentHeader from "../Common/ApartmentHeader"
import CommonStyle from "../Common/Style"
import UserStyle from "../User/UserStyle"
import { useContext, useEffect, useState } from "react"
import { MyUserContext } from "../../configs/Context"
import { useNavigate, useParams, useSearchParams } from "react-router-dom"
import { authApi, endpoint } from "../../configs/APIs"

import Modal from "@mui/material/Modal"
import Box from '@mui/material/Box'
import Button from 'react-bootstrap/Button';
import Spinner from "react-bootstrap/Spinner"
import Form from "react-bootstrap/Form"
import TextField from "@mui/material/TextField"
import { DemoContainer } from '@mui/x-date-pickers/internals/demo';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import dayjs from "dayjs"

const RelativeRegistry = () => {
    const user = useContext(MyUserContext)
    const { id } = useParams()
    const [q, ] = useSearchParams()
    const nav = useNavigate()

    const [data, setData] = useState(null)

    const [modal, SetModal] = useState(false)
    const [error, setError] = useState("")
    const [modalReg, SetReg] = useState(false)
    const [navable, SetNavable] = useState(true)
    const [reload, setReload] = useState(true)
    const currentDate = new Date();

    const [registry, SetRegistry] = useState(null)
    const [kw, setKw] = useState("")

    useEffect(() => {
        SetRegistry({
        apartmentId: id,
        startDate: `${currentDate.getDate()}/${currentDate.getMonth()+1}/${currentDate.getFullYear()}`,
        endDate: `${currentDate.getDate()}/${currentDate.getMonth()+1}/${currentDate.getFullYear()}`
    })
        if (user === null)
            nav('/login')
        else
            fetchData()
    }, [id, reload])

    const fetchData = async () => {
        var path = endpoint['relative_registry'](id)
        var pk = q.get('kw')
        if(pk!==null && pk!=="" ){
            path = `${path}?kw=${pk}`
            setKw(pk)
        }
        console.log(path)
        const res = await authApi().get(path)
        if (typeof res.data === "string") {
            setError(res.data);
            SetModal(true)
            SetNavable(true)
        }
        else
            setData(res.data)
    }

    const submit = async (e) => {
        e.preventDefault();
        const res = await authApi().post(endpoint['relative_Register'], registry)
        if(res.status === 200)
        {
            setError(res.data)
            SetModal(true)
            SetNavable(false)
        }
        else if(res.status === 201){
            setReload(!reload)
            SetReg(false)
        }
            
        console.log(registry)
    }

    const search = (e) => {
        e.preventDefault()
        
        nav(`/apartments/${id}/relative_registries/?kw=${kw}`)
        setReload(!reload)
    }
    return <div>
        <ApartmentHeader />
        <div style={{ ...CommonStyle.Component, height: data === null ? 300 : Math.ceil(data.length / 2) * 300 }}>
            <div style={{ margin: 20, ...CommonStyle.flex_space}}>
                <div style={{ ...CommonStyle.flex_space, width: "30%" }}>
                    <div>
                        <h4>Đăng kí khách</h4>
                        <div>Đăng kí cho khách đễn chung cư </div>
                    </div>
                    <button onClick={() => SetReg(true)} className="btn btn-primary">Tạo mới</button>
                </div>

                
                <div style={{ ...CommonStyle.flex_space, width: "30%" }}>
                    <TextField required id="outlined-required" placeholder="Nhập tên bạn muốn tìm...." color="warning" value={kw} onChange={(e) => { setKw(e.target.value) }} />
                    <Button onClick={search} className="btn-warning">Tìm kiếm</Button>
                </div>
            </div>

            <Stack direction="row" flexWrap={"wrap"} width="100%" >
                {data === null ? <div style={{ ...CommonStyle.flex_center, width: "100%", height: "100%" }}><Spinner animation="grow" variant="primary" /></div> : data.map((x) =>
                    <div key={x.id} style={{ display: "flex", ...CommonStyle.Box, margin: "20px 40px", width: 500, height: 200, borderRadius: 40 }}>
                        <div style={{ backgroundImage: "url(https://i.pinimg.com/474x/a9/c9/13/a9c913c311cb61602075bfd6c6c70a99.jpg)", backgroundSize: "100%", borderRadius: "40px 0px 0px 40px", width: 100 }}></div>
                        <div style={{ ...CommonStyle.flex_center, alignItems: "flex-start", flexDirection: "column", padding: 10 }}>
                            <h4><br /><strong>{x.name}</strong></h4>
                            <div>Được đăng kí ra vào chung cư:</div>
                            <div style={{ width: 350, textAlign: "center", fontSize: 20 }}><strong>{x.startDate}</strong> {"->"} <strong>{x.endDate}</strong></div>
                            <div style={{ marginLeft: "75%", marginTop: 20 }}>{x.active === 1 ? <p className="bg-success text-light rounded">Chấp nhận</p> : <p className="bg-danger text-light rounded">Từ chối</p>}</div>
                        </div>
                    </div>
                )}
            </Stack>
        </div>

        <Modal open={modal} onClose={() => { SetModal(false); nav('/'); }} aria-labelledby="child-modal-title" aria-describedby="child-modal-description">
            <Box sx={{ ...UserStyle.modal, width: 600 }}>
                <h2 id="child-modal-title" className="text-danger">Thông báo lỗi</h2>
                <p id="child-modal-description">
                    {error}
                </p>
                <Button onClick={() => { SetModal(false); if (navable) { nav('/') }; }}>Trở về trang chủ</Button>
            </Box>
        </Modal>

        <Modal open={modalReg} onClose={() => { SetReg(false) }} aria-labelledby="child-modal-title" aria-describedby="child-modal-description">
        {registry === null ? <></> : <Box sx={{ ...UserStyle.modal, width: 600 }}>
                <h2 id="child-modal-title" className="text-primary">ĐĂNG KÍ MỚI</h2>
                <div style={{ ...CommonStyle.Box, height: 2, width: "100%", margin: "20px 0px" }}></div>
                <p id="child-modal-description">
                    <Form onSubmit={submit}>
                        <TextField required id="outlined-required" label="Họ và tên" color="warning" onChange={(e) => { SetRegistry({ ...registry, "name": e.target.value }) }} />
                        <div style={{ ...CommonStyle.Box, height: 2, width: "100%", margin: "20px 0px" }}></div>
                        <LocalizationProvider dateAdapter={AdapterDayjs}>
                            <DemoContainer components={['DatePicker', 'DatePicker']}>
                                <DatePicker  format="DD/MM/YYYY" label="Ngày bắt đầu" onChange={(n) => SetRegistry({ ...registry, "startDate": `${n.get('date')}/${n.get('month') + 1}/${n.get('year')}` })} />
                                <DatePicker  format="DD/MM/YYYY" label="Ngày kết thúc" onChange={(n) => SetRegistry({ ...registry, "endDate": `${n.get('date')}/${n.get('month') + 1}/${n.get('year')}` })} />
                            </DemoContainer>
                        </LocalizationProvider>
                        <div style={{ ...CommonStyle.Box, height: 2, width: "100%", margin: "20px 0px" }}></div>
                        <div style={{ ...CommonStyle.flex_space }}>
                            <Button className="btn-danger" onClick={() => SetReg(false)}>Đóng</Button>
                            <Button type="submit">Đăng kí</Button>
                        </div>
                    </Form>
                </p>
            </Box>
            }
        </Modal>
        
    </div>
}

export default RelativeRegistry