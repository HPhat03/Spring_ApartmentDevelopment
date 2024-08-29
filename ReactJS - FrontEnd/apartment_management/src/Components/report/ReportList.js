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
import Toast from 'react-bootstrap/Toast';

const ReportList = () => {
    const user = useContext(MyUserContext)
    const { id } = useParams()
    const [q,] = useSearchParams()
    const nav = useNavigate()

    const [data, setData] = useState(null)

    const [modal, SetModal] = useState(false)
    const [error, setError] = useState("")


    const [reload, setReload] = useState(true)
    const [page, setPage] = useState(1)
    const [created, setCreated] = useState(false)

    useEffect(() => {
        if (user === null)
            nav('/login')
        else
            fetchData()
        if (q.get('created') === "true") {
            setCreated(true)
        }
    }, [id, reload])

    const fetchData = async () => {
        setData(null)
        var p = q.get('page')
        var path = `${endpoint['reports'](id)}?page=${p}`
        setPage(parseInt(p))

        const res = await authApi().get(path)
        if (typeof res.data === "string") {
            setError(res.data);
            SetModal(true)
        }
        else
            setData(res.data)
    }

    const next = () => {
        nav(`/apartments/${id}/reports/?page=${page + 1}`)
        setReload(!reload)
    }
    const pre = () => {
        nav(`/apartments/${id}/reports/?page=${page - 1}`)
        setReload(!reload)
    }
    return (
        <div>
            <ApartmentHeader />
            <div style={{ ...CommonStyle.flex_center, padding: "0px 100px", width: "100%", height: 570 ,marginTop: 40 }}>
                <div style={{ ...CommonStyle.contentBlock, ...CommonStyle.Box, marginLeft: 10, width: "90%" }}>
                    <Button onClick={() => nav(`/apartments/${id}/reports/new/`)} className="btn btn-danger m-2">Báo cáo</Button>
                    {data === null ? <div style={{ ...CommonStyle.flex_center, height: "100%" }}><Spinner animation="grow" variant="primary" /></div> : data.map((x) => {
                        return <div key={x.id} onClick={() => nav(`/reports/${x.id}/`)} style={{ ...CommonStyle.flex_space, ...CommonStyle.Box, padding: 15, marginBottom: 10 }}>
                            <div>
                                <h4>{x.title}</h4>
                                <div>Mã báo cáo: {x.id}</div>
                            </div>

                            <div>Tạo ngày {x.createdDate}</div>
                        </div>
                    })}
                    {data == null ? <></> :
                        <div style={{ ...CommonStyle.flex_space }}>
                            <Button onClick={pre} disabled={page === 1 ? true : false} variant="outline-dark">Quay lại</Button>
                            <Button onClick={next} disabled={data.length < 4 || data == []} variant="outline-dark">Tiếp theo</Button>
                        </div>
                    }

                </div>
            </div>

            <Modal open={modal} onClose={() => { SetModal(false); nav('/'); }} aria-labelledby="child-modal-title" aria-describedby="child-modal-description">
                <Box sx={{ ...UserStyle.modal, width: 600 }}>
                    <h2 id="child-modal-title" className="text-danger">Thông báo lỗi</h2>
                    <p id="child-modal-description">
                        {error}
                    </p>
                    <Button onClick={() => { SetModal(false); nav('/'); }}>Trở về trang chủ</Button>
                </Box>
            </Modal>

            <Toast show={created} bg="success" style={{position: "fixed", top: 0, left: window.innerWidth - 375}} onClose={() => setCreated(false)}>
                <Toast.Header>
                    <img
                        src="holder.js/20x20?text=%20"
                        className="rounded me-2"
                        alt=""
                    />
                    <strong className="me-auto">PNApartment</strong>
                    <small>vừa xong</small>
                </Toast.Header>
                <Toast.Body className="text-light">Tạo thành công</Toast.Body>
            </Toast>
        </div>
    )
}

export default ReportList