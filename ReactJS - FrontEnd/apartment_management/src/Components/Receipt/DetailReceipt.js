import { useNavigate, useParams } from "react-router-dom"
import ApartmentHeader from "../Common/ApartmentHeader"
import { useContext, useEffect, useState } from "react"
import { authApi, endpoint } from "../../configs/APIs"
import Spinner from "react-bootstrap/Spinner"
import CommonStyle from "../Common/Style"
import { CurrentApartmentContext, MyUserContext } from "../../configs/Context"
import Button from "react-bootstrap/Button"

const DetailReceipt = () => {
    const {id} = useParams()
    const [data, setData] = useState(null);
    const current = useContext(CurrentApartmentContext)
    const user = useContext(MyUserContext)
    const nav = useNavigate()
    useEffect(() => {
        if(user===null)
            nav('/login')
        fetchData()
    }, [id])

    const fetchData = async () => {
        const res = await authApi().get(endpoint['detail_receipts'](id))
        setData(res.data)
    }

    const VNPay = async () => {
        const res = await authApi().get(`${endpoint['vnpay']}?amount=${data.total}&receipt_id=${data.id}`)
        window.location.href = res.data.url;
    }
    return <div>
        <ApartmentHeader />
        <div style={{ ...CommonStyle.flex_center, padding: "0px 100px", width: "100%", padding: 10, height: data===null ? 300 : "auto" }}>
            {data===null ? <div style={{ ...CommonStyle.flex_center, height: "100%" }}><Spinner animation="grow" variant="primary" /></div> : 
            <div style={{...CommonStyle.Box, height: "100%", width: "50%", margin: 10, padding: 10}}>
                <h3 style={{textAlign: "center"}}>{data.name}</h3>
                <br></br>
                <h4>1. Thông tin hóa đơn</h4>
                <div>
                    <div>- Đây là hóa đơn của phòng {current.roomNumber} với mã hóa đơn <strong>{data.id}</strong></div>
                    <div>- Tháng: <strong>{data.month}</strong></div>
                    <div>- Năm: <strong>{data.year}</strong></div>
                    <div>- Ngày tạo: <strong>{data.createdDate}</strong></div>
                    <div>- Trạng thái: <strong className={`text-${data.paid ? "success" : "danger"}`}>{data.paid ? "Đã thanh toán" : "Chưa thanh toán"}</strong></div>
                </div>
                <br></br>
                <h4>2. Chi tiết hóa đơn</h4>
                <div>
                    <div>- Mức độ sử dụng (tính đến thời điểm tạo hóa đơn): </div>
                    {data.usage.map((x) => <div style={{marginLeft: 50}}>- Số đồng hồ {x.type == "ELECTRIC" ? "điện" : "nước"}: <strong>{x.number}</strong></div>)}
                    <div>- Danh mục chi tiết hóa đơn</div>
                    <div style={{...CommonStyle.flex_center, flexDirection: "column"}}>
                        {data.details.map((x) => <div style={{...CommonStyle.flex_space, width: "70%"}}>
                            <strong>{x.content}</strong>
                            <div>{x.price}</div>
                        </div>)}
                        <div style={{...CommonStyle.Box, height: 2, width: "70%"}}></div>
                        <div style={{...CommonStyle.flex_space, width: "70%"}}>
                            <strong className="text-success">Tổng cộng</strong>
                            <div>{data.total}</div>
                        </div>
                    </div>
                    <br></br>
                    
                </div>
            </div>
            }
            <div style={{...CommonStyle.Box, height: "100%", ...CommonStyle.flex_center, flexDirection: "column", width: "15%", margin: 10, padding: 10}}>
                <Button className="btn btn-primary" disabled={data === null ? false : data.paid} onClick={VNPay}>Thanh toán VN-PAY</Button>
                {/* <br/>
                <Button className="btn btn-secondary" disabled={data === null ? false : data.isPaid}>Thanh toán thường</Button> */}
            </div>
        </div>
    </div>
}
export default DetailReceipt