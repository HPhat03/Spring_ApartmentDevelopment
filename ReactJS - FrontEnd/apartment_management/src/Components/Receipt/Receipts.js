import { useContext, useEffect, useState } from "react"
import { MyUserContext } from "../../configs/Context"
import { useNavigate, useParams, useSearchParams } from "react-router-dom"
import ApartmentHeader from "../Common/ApartmentHeader"
import CommonStyle from "../Common/Style"
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import { authApi, endpoint } from "../../configs/APIs"
import Modal from "@mui/material/Modal"
import Box from '@mui/material/Box'
import UserStyle from "../User/UserStyle"
import Spinner from "react-bootstrap/Spinner"

const Receipts = () => {
    const user = useContext(MyUserContext)
    const { id } = useParams();
    const [p,] = useSearchParams();
    const [receipts, setReceipts] = useState(null);
    const nav = useNavigate()
    const [modal, SetModal] = useState(false);
    const [error, SetError] = useState("");

    //FILTER
    const [monthFilter, setMonth] = useState(null);
    const [yearFilter, setYear] = useState(null);
    const [stateFilter, setFilter] = useState(null);
    const [page, setPage] = useState(1);
    const [reload, setReload] = useState(false)
    const months = () => {
        let m = []
        for (var i = 1; i <= 12; i++)
            m.push(i)
        return m
    }
    const state = [{ title: "Đã thanh toán", value: "PAID" }, { title: "Chưa thanh toán", value: "UNPAID" }]
    const years = () => {
        const startYear = 2017;
        const currentYear = new Date().getFullYear();

        const year = []
        for (var i = currentYear; i >= startYear; i--)
            year.push(i)
        return year
    }
    useEffect(() => {
        if (user == null)
            nav('/login/')
        else
            fetchData()
    }, [id, reload])

    const fetchData = async () => {
        setReceipts(null)

        let url = `${endpoint['receipts'](id)}?page=${p.get("page")}`
        setPage(parseInt(p.get("page")))
        if (p.get("month") != null)
            url = `${url}&month=${p.get("month")}`
        if (p.get("year") != null)
            url = `${url}&year=${p.get("year")}`
        if (p.get("filter") != null)
            url = `${url}&filter=${p.get("filter")}`
        console.log(url);
        const res = await authApi().get(url);
        console.log(res.data)
        if (typeof res.data === "string") {
            SetError(res.data);
            SetModal(true);
        }
        else
            setReceipts(res.data)
    }

    const submit = (e) => {
        e.preventDefault();
        let url = `/apartments/${id}/receipts/?page=${page}`
        if (monthFilter !== null && monthFilter !== "")
            url = `${url}&month=${monthFilter}`
        if (yearFilter !== null && yearFilter !== "")
            url = `${url}&year=${yearFilter}`
        if (stateFilter !== null)
            url = `${url}&filter=${stateFilter}`

        nav(url);
        setReload(-reload);
    }

    const cancel = (e) => {
        e.preventDefault();
        setMonth(null)
        setYear(null)
        setFilter(null)
        nav(`/apartments/${id}/receipts/?page=${page}`)
        setReload(-reload);
    }

    const next = (e) => {
        e.preventDefault();
        if (receipts != [] && receipts.length === 4) {
            setMonth(null)
            setYear(null)
            setFilter(null)
            nav(`/apartments/${id}/receipts/?page=${page + 1}`)
            setReload(-reload);
        }
    }

    const pre = (e) => {
        e.preventDefault();
        if (page !== 1) {
            setMonth(null)
            setYear(null)
            setFilter(null)
            nav(`/apartments/${id}/receipts/?page=${page - 1}`)
            setReload(-reload);
        }
    }

    const navTo = (e, id) => {
        e.preventDefault()

        nav(`/receipts/${id}/`);
    }
    return (
        <div>
            <ApartmentHeader />
            <div style={{ ...CommonStyle.flex_space, padding: "0px 100px", width: "100%", height: 500, marginTop: 40}}>
                <div style={{ ...CommonStyle.contentBlock, ...CommonStyle.Box, ...CommonStyle.flex_center, marginLeft: 10, width: "20%" }}>
                    <div><Form onSubmit={submit}>
                        <div>BỘ LỌC</div>
                        <Form.Group className="mb-3" controlId="month">
                            <Form.Label><strong>Tháng</strong></Form.Label>
                            <Form.Select aria-label="Default select example" onChange={(e) => setMonth(e.target.value)}>
                                <option value="" selected={monthFilter === null}>Chọn</option>
                                {months().map((x, i) => { return <option value={x} selected={monthFilter === x}>Tháng {x}</option> })}
                            </Form.Select>
                        </Form.Group>
                        <div style={{ ...CommonStyle.Box, height: 1 }} ></div>
                        <Form.Group className="mb-3" controlId="month">
                            <Form.Label><strong>Năm</strong></Form.Label>
                            <Form.Select aria-label="Default select example" onChange={(e) => setYear(e.target.value)}>
                                <option value="" selected={yearFilter === null}>Chọn</option>
                                {years().map((x, i) => { return <option value={x} selected={yearFilter === x}>{x}</option> })}
                            </Form.Select>
                        </Form.Group>
                        <div style={{ ...CommonStyle.Box, height: 1 }} ></div>
                        <Form.Group className="mb-3" controlId="month">
                            <Form.Label><strong>Loại hóa đơn</strong></Form.Label>
                            <div key={`inline-radio`} className="mb-3">
                                {state.map((type, i) => (
                                    <Form.Check
                                        label={type.title}
                                        name="filter"
                                        type='radio'
                                        id={`inline-radio-${i}`}
                                        value={type.value}
                                        onChange={(e) => setFilter(e.target.value)}
                                        checked={stateFilter === type.value}
                                    />
                                ))}
                            </div>
                        </Form.Group>
                        <div style={{ ...CommonStyle.Box, height: 1 }} ></div>
                        <Button type="submit" className="btn btn-warning mt-2 mb-2" style={{ width: "100%" }}>Lọc</Button>
                        <div style={{ ...CommonStyle.Box, height: 1 }} ></div>
                        <Button onClick={cancel} className="btn btn-danger mt-2 mb-2" style={{ width: "100%" }}>Hủy</Button>
                    </Form></div>

                </div>
                <div style={{ ...CommonStyle.contentBlock, ...CommonStyle.Box, marginLeft: 10, width: "80%" }}>
                    {receipts === null ? <div style={{ ...CommonStyle.flex_center, height: "100%" }}><Spinner animation="grow" variant="primary" /></div> : receipts == [] ? <div style={{ ...CommonStyle.flex_center, height: "100%" }}><h3>Hiện không có hóa đơn</h3></div> : receipts.map((x, i) => {
                        return <div onClick={(e) => navTo(e, x.id)} style={{ ...CommonStyle.flex_space, ...CommonStyle.Box, padding: 15, marginBottom: 10 }}>
                            <div>
                                <h4>{x.name}</h4>
                                <div>Mã hóa đơn: {x.id}</div>
                            </div>

                            <div>Tạo ngày {x.createdDate}</div>
                        </div>
                    })}
                    {receipts == null ? <></> :
                        <div style={{ ...CommonStyle.flex_space }}>
                            <Button onClick={pre} disabled={page === 1 ? true : false} variant="outline-dark">Quay lại</Button>
                            <Button onClick={next} disabled={receipts.length < 4 || receipts == []} variant="outline-dark">Tiếp theo</Button>
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
        </div>
    )
}

export default Receipts