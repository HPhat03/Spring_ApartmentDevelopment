import Spinner from "react-bootstrap/Spinner"
import CommonStyle from "../Common/Style"
import { useNavigate, useParams, useSearchParams } from "react-router-dom"
import { useEffect, useState } from "react"
import { authApi, endpoint } from "../../configs/APIs"

const Validate = () => {
    const {id} = useParams()
    const [q, ] = useSearchParams()
    const nav = useNavigate()
    useEffect(() =>{
        if(q.get('vnp_TransactionNo') in [null, undefined])
            nav(`/receipts/${id}/`)
        else{
            setTimeout(validate, 2000)
        }
    }, [id])

    const validate = async () => {
        const data = {"receiptId": id, "type": "VNPAY", "transactionId": q.get('vnp_TransactionNo')}
        console.log(data)
        const res = await authApi().post(endpoint['validate_payment'], data)
        if(res.status === 200)
        {
            alert(res.data)
        }
        nav(`/receipts/${id}/`)
    }
    return <div style={{width: "100%", height: 600}}>
         <div style={{ ...CommonStyle.flex_center, height: "100%" }}>Đang xử lý thanh toán của bạn <Spinner style={{marginLeft: 20}} animation="grow" variant="primary" /></div>
    </div>
}

export default Validate