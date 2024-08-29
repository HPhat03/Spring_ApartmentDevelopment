
import CommonStyle from "./Style";
import Stack from '@mui/material/Stack';
import { useContext, useEffect } from "react";
import { MyChatToggleDispatchContext, MyUserContext } from "../../configs/Context";
import LoginedHome from "./LoginedHome";
import cookie from "react-cookies"
import { Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
const Home = () => {  
    const user = useContext(MyUserContext);
    const toggle = useContext(MyChatToggleDispatchContext);
    const nav = useNavigate();
    if(user!= null)
        return <LoginedHome/>
    return (
        <div className="Home">
            
            <Stack id="reg" spacing={4} style={{...CommonStyle.mainBg, padding:"10px", height: 300}}>
                <Stack style={{margin: "20px"}} direction="row" justifyContent="space-between" alignItems="center">
                    <h3>PNApartment.com</h3>
                    <Stack direction="row" spacing={2}>
                        <a className="d-flex align-items-center btn btn-primary" onClick={() => nav("/login/")}>Đăng nhập</a>
                        <div><i class="bi bi-list btn text-light" style={{fontSize: 30}}></i></div>
                    </Stack>
                </Stack >
                <div style={{marginLeft: "10%"}}>
                    <h1 style={{fontSize: 40, marginBottom: 0}}>ĐẾN VỚI CHUNG CƯ PNA</h1>
                    <div style={{fontSize: 20}}>Giá cá hợp lí, dịch vụ tiện ích</div>
                </div>
            </Stack>
            <div style={CommonStyle.contactBar} >
                <Stack direction="row" alignItems="center" spacing={1} style={{backgroundColor: "#E4A11B", borderRadius: 10, padding: 5, width: "60%"}}>
                    <input style={{ height: 50, width: "90%", borderRadius: 5}} placeholder="Tên của bạn..."></input>
                    <input style={{ height: 50, width: "50%", borderRadius: 5}} placeholder="Số điện thoại..."></input>
                    <button style={{ height: 50, width: "30%", borderRadius: 10}} className="btn btn-primary">Liên hệ ngay!</button>
                </Stack>
            </div>

            <div style={CommonStyle.Component}>
                <div style={{margin: 20}}>
                    <h4>OFFER</h4>
                    <div>Ưu đãi cho thành viên của PNA</div>
                </div>
                <Stack direction="row">
                    <div style={{...CommonStyle.Box, ...CommonStyle.OfferBox, width: "50%", height: 200}}>
                        <div>
                            <div style={{margin: 10}}>
                                <h4>Bạn sẵn sàng cho môi trường mới?</h4>
                                <div>Liên hệ với mức giá hợp lí</div>
                                <button className="btn btn-primary mt-3">Liên hệ</button>
                            </div>
                        </div>
                        <img src="https://cdn-merchant.vinid.net/images/gallery/omre_trang_tin_tuc/1691393084_1.jpg" width={400} height={150} />
                    </div>
                    <div style={{...CommonStyle.Box, ...CommonStyle.OfferBox, width: "50%"}}>
                        <div>
                            <div style={{margin: 10}}>
                                <h4>Từ ngày 1-10 hàng tháng</h4>
                                <div>Các dịch vụ công sẽ được miễn phí</div>
                                <button className="btn btn-primary mt-3">Liên hệ</button>
                            </div>
                        </div>
                        <img src="https://static.secureholiday.net/static/Pictures/4223/00000683767.jpg?w=500&format=webp" height={150} width={300}/>
                    
                    </div>
                </Stack>
            </div>

            <div style={{...CommonStyle.Component, height: 500}}>
                <div style={{marginLeft: 20}}>
                    <h4>Life Style</h4>
                    <div>Nhịp sống tại PNA</div>
                </div>
                <Stack direction="row" justifyContent="space-between" style={{margin: 10}}>
                    <div style={{...CommonStyle.lifeStyle, backgroundImage: "url(https://www.rentcafe.com/blog/wp-content/uploads/sites/62/2020/08/NorthShore-2.png)",  width: "50%", marginRight: 5}}><h4>Không gian xanh</h4></div>
                    <div style={{...CommonStyle.lifeStyle, backgroundImage: "url(https://media.equityapartments.com/images/c_crop,x_0,y_0,w_1920,h_1080/c_fill,w_737,h_414/q_auto,f_auto/4089-21/mariposa-at-playa-del-rey-apartments-swimming-pool)", width: "50%"}}><h4>Hồ bơi</h4></div>
                </Stack>
                <Stack direction="row" justifyContent="space-between" style={{margin: 10}}>
                    <div style={{...CommonStyle.lifeStyle, backgroundImage: "url(https://images.squarespace-cdn.com/content/v1/5ada11772714e5eb213ab1df/1654290316961-1G3DBLPINKF8KJ4QD2WH/Web_09.jpg?format=1000w)", width: "33%"}}><h4>Thể dục</h4></div>
                    <div style={{...CommonStyle.lifeStyle, backgroundImage: "url(https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS5PiwxNcP5bhx-TNqmdyreepbYxv0Tvt96wQ&s)", width: "33%"}}><h4>Tiện lợi</h4></div>
                    <div style={{...CommonStyle.lifeStyle, backgroundImage: "url(https://blog.cort.com/wp-content/uploads/2020/03/people-gathered-inside-house-sitting-on-sofa-1054974.jpg)",width: "33%"}}><h4>Hòa nhã</h4></div>
                </Stack>
            </div>
            
            <div style={{marginTop: 80, marginBottom: 80, height: 400, backgroundColor: "#001028", color: "white"}}>
                <Stack direction="row" spacing={100} justifyContent="center" alignItems="center" style={{marginTop: 45}}>
                    <div>
                        <h1>Giới thiệu tính năng mới</h1>
                        <div>PNBot - Trợ lí ảo đáng tin cậy</div>
                    </div>
                    <Stack spacing={5} justifyContent="center" alignItems="center" style={{margin: 25, height: 350, width: 400}}>
                        <img src="https://img.freepik.com/free-vector/chatbot-chat-message-vectorart_78370-4104.jpg" width={200} height={200} style={{borderRadius: 50}}/>
                        <Button onClick={() => {toggle({type: "on"})}} className="btn btn-success">Trải nghiệm</Button>
                    </Stack>
                </Stack>
            </div>

            <Stack  pacing={10} justifyContent="center" alignItems="center" style={{marginTop: 80, marginBottom: 80, height: 300, backgroundColor: "#001028", color: "white"}}>
                <h1>Đăng nhập ngay để quản lý chung cư của bạn</h1>
                <a style={{width: 200, height: 50}}className="d-flex align-items-center justify-content-center btn btn-primary" onClick={() => {nav('/login/')}}>Đăng nhập</a>
                <h2 style={{color:"#E4A11B"}}>Hoặc trở thành thành viên mới của PNApartment</h2>
                <a style={{width: 100, height: 50}}className="d-flex align-items-center justify-content-center btn btn-warning" href="#reg">Liên hệ</a>
            </Stack>

            
        </div>
    );
}

export default Home;