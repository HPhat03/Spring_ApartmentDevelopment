import { useContext, useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import { CurrentApartmentDispatcherContext, MyUserContext } from "../../configs/Context"
import ApartmentHeader from "./ApartmentHeader"
import { authApi, endpoint } from "../../configs/APIs"
import CommonStyle from "./Style"
import cookie from "react-cookies"

const Apartment = () => {
    const { id } = useParams()
    const [data, setData] = useState(null);
    const user = useContext(MyUserContext);
    const nav = useNavigate()
    const formatter = new Intl.NumberFormat('vn', {
        style: 'currency',
        currency: 'VND',
    })
    useEffect(() => {
        if (user == null)
            nav('/login/')
        else
            fetchData()
        
    }, [id])


    const fetchData = async () => {
        const res = await authApi().get(endpoint['detail_apartment'](id));
        setData(res.data)
        
    }
    return <div>
        {data == null ? <></> : <div><ApartmentHeader></ApartmentHeader>
           

            <div style={{...CommonStyle.flex_space, padding: "0px 100px", width: "100%", height: 500 }}>
                <div style={{...CommonStyle.contentBlock,...CommonStyle.Box, ...CommonStyle.flex_center, marginLeft: 10}}>
                    <h4 style={{fontWeight: "bold"}}>Thông tin hợp đồng.</h4>
                    <div>
                        <div>Mã Phòng: {data.room.id}</div>
                        <div>Tên phòng: Phòng {data.room.roomNumber}</div>
                        <div>Thuộc chung cư PNA - Khu dân cư, Nhà Bè, Thành phố Hồ Chí Minh</div>
                        <div>   Hợp đồng cam kết cho thuê phòng {data.room.roomNumber}, cùng với các dịch vụ công được hỗ trợ bởi chung cư PNA. Hợp đồng thuê theo tháng với mức giá phòng là {formatter.format(data.finalPrice)} (chưa bao gồm các dịch vụ). Hợp đồng có hiệu lực từ ngày {data.createdDate}</div>
                        <div> - Đối với các trường hợp vi phạm hợp đồng (không thanh toán tiền đúng thời hạn nhiều lần, có các hành động gây nguy hiểm đến tập thể) đều được xử lý theo pháp luật và chấm dứt.</div>
                    </div>
                    <div>___________________________________________________________________</div>
                    <h4 style={{fontWeight: "bold"}}>Các dịch vụ:</h4>
                    {data.services == [] ? <></> : data.services.map((x) => {
                        return (
                            <div key={x.id} style={{...CommonStyle.flex_space, width: "100%"}}>
                                <div>{x.name} :</div>
                                <div>{formatter.format(x.price)}</div>
                            </div>
                        )
                    })}
                </div >
                <div style={{...CommonStyle.contentBlock,...CommonStyle.Box, ...CommonStyle.flex_center}}>
                    <h4 style={{fontWeight: "bold"}}>Người thuê</h4>
                    <div>
                        <div style={{marginLeft: "30%", backgroundImage: `url(${data.resident.avatar})`, backgroundSize: "100%", width: 200, height: 200}}></div>
                        <div> Tôi tên: {data.resident.name} đồng ý đăng kí hợp đồng, và cam kết thực hiện đúng nguyên tắc.</div>
                    </div>
                    <h4 style={{fontWeight: "bold"}}>Thông tin hộ gia đình</h4>
                    {data.other_members == [] ? <div>Hiện không có hộ gia đình</div> : data.other_members.map((x) => {
                        return (
                            <div key={x.id} style={{...CommonStyle.flex_space, width: "100%"}}>
                                <div>{x.name} :</div>
                                <div>Mối quan hệ: {x.relationship}</div>
                            </div>
                        )
                    }) }
                </div>
            </div>
        </div>}
    </div>
}
export default Apartment