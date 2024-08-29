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

import Carousel from "react-multi-carousel";
import "react-multi-carousel/lib/styles.css";

export const responsive = {
    superLargeDesktop: {
      // the naming can be any, depends on you.
      breakpoint: { max: 4000, min: 3000 },
      items: 5
    },
    desktop: {
      breakpoint: { max: 3000, min: 1024 },
      items: 3
    },
    tablet: {
      breakpoint: { max: 1024, min: 464 },
      items: 2
    },
    mobile: {
      breakpoint: { max: 464, min: 0 },
      items: 1
    }
  };
const SmartCabinet = () => {
    const user = useContext(MyUserContext)
    const nav = useNavigate()
    const [pendings, setPendings] = useState([])
    const [receivces, setReceivce] = useState([])
    const {id} = useParams()
    const [modal, SetModal] = useState(false)
    const [error, setError] = useState("")

    useEffect(() => {
        if(user===null)
            nav('/login')
        else
            fetchData()
    }, [id])
    
    const fetchData = async () => {
        var [p,r] = [[],[]]
        const res = await authApi().get(endpoint['cabinets'](id))
        
        if(typeof res.data === "string")
        {
            setError(res.data);
            SetModal(true)
        }
        else
        for (let i = 0; i < res.data.length; i++) {
            if(res.data[i].status==="PENDING")
                    p.push(res.data[i])
                else    
                    r.push(res.data[i])
            
        }
        console.log(p, r)
        setPendings(p); setReceivce(r);
    }
    return <div>
        <ApartmentHeader />
        <div style={CommonStyle.Component}>
            <div style={{ margin: 20 }}>
                <h4><i class="bi bi-mailbox2-flag"></i> Hàng chờ nhận </h4>
                <div>Bạn có đơn hàng đang chờ nhận ở tủ đồ thông minh</div>
            </div>
            
            <Carousel responsive={responsive} className="bg-secondary rounded">
                {pendings.map((x) =><div style={{backgroundColor: "#D7D7D7", display: "flex", flexDirection: "row", alignItems: "center", margin: 20, height: 200 ,...CommonStyle.Box}}>
                    <div style={specialStyle}></div>
                    <div> 
                        <div style={{}}>Mô tả: <strong>{x.decription}</strong></div>
                        <br/>
                        <div>Trạng thái: <span className={`text-light rounded p-1 bg-${x.status!=="PENDING" ? "success" : "warning"}`}>{x.status}</span></div>
                        <br/>
                        <div style={{fontWeight: "lighter", fontSize: 14}}>Ngày nhận: {x.createdDate}</div>
                        <div style={{fontWeight: "lighter", fontSize: 14}}>Ngày cập nhật: {x.updatedDate}</div>
                    </div>
                </div>)}
            </Carousel>

        </div>
        <div style={{...CommonStyle.Component, margin: "-75px 75px 75px 75px"}}>
            <div style={{ margin: 20 }}>
                <h4><i class="bi bi-mailbox2"></i> Hàng đã nhận </h4>
                <div>Những đơn hàng đã được nhận tại tủ đồ thông minh</div>
            </div>
            
            <Carousel responsive={responsive} className="bg-secondary rounded">
            {receivces.map((x) => <div style={{backgroundColor: "#D7D7D7", display: "flex", flexDirection: "row", alignItems: "center", margin: 20, height: 200 ,...CommonStyle.Box}}>
                    <div style={specialStyle}></div>
                    <div> 
                        <div style={{}}>Mô tả: <strong>{x.decription}</strong></div>
                        <br/>
                        <div>Trạng thái: <span className={`text-light rounded p-1 bg-${x.status!=="PENDING" ? "success" : "warning"}`}>{x.status}</span></div>
                        <br/>
                        <div style={{fontWeight: "lighter", fontSize: 14}}>Ngày nhận: {x.createdDate}</div>
                        <div style={{fontWeight: "lighter", fontSize: 14}}>Ngày cập nhật: {x.updatedDate}</div>
                    </div>
                </div>)}
            </Carousel>

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
}

const specialStyle = {
    backgroundImage: "linear-gradient(to right, black, white), url(https://vft.vn/wp-content/uploads/2024/03/lotte-phutho-1-800x600.jpg)",
    backgroundBlendMode: "lighten", 
    backgroundSize: "150%",
    width: "40%",
    height: "90%",
    margin: "0px 10px"
}
export default SmartCabinet