
import { useContext } from 'react';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { useNavigate } from "react-router-dom";
import { CurrentApartmentContext } from '../../configs/Context';

const ApartmentHeader = () => {
        const nav = useNavigate();
        const current = useContext(CurrentApartmentContext)
        const path = () => {
            return current!==null && current !== undefined ? current.id : ""
        }

        return (
            <>
            <div className="d-flex flex-direction-row"style={{backgroundImage: "linear-gradient(black, white), url(https://media.self.com/photos/630635c30b7f36ce816f374a/4:3/w_2560%2Cc_limit/DAB03919-10470989.jpg)", width: "100%", height:400, backgroundBlendMode: "lighten", backgroundSize: "100%"}}>
                <img style={{margin: "150px 0px 0px 100px", boder: "solid grey 10px", borderRadius: 10}} src="https://media.self.com/photos/630635c30b7f36ce816f374a/4:3/w_2560%2Cc_limit/DAB03919-10470989.jpg" width={300} height={200}/>
                <h2 style={{margin: "300px 10px"}}>CHUNG CƯ PNA - PHÒNG {current!==null && current !== undefined ? current.roomNumber : ""}</h2>
            </div>
            <Navbar bg="light" data-bs-theme="light">
                <Container>
                    <Nav className="me-auto">
                        <Nav.Link onClick={() => {nav(`/apartments/${path()}/`)}}>Hợp đồng</Nav.Link>
                        <Nav.Link onClick={() => {nav(`/apartments/${path()}/receipts/?page=1`)}}>Hóa đơn</Nav.Link>
                        <Nav.Link onClick={() => {nav(`/apartments/${path()}/relative_registries/`)}}>Đăng kí người thân</Nav.Link>
                        <Nav.Link onClick={() => {nav(`/apartments/${path()}/cabinets/`)}}>Tủ thông minh</Nav.Link>
                        <Nav.Link className="text-danger" onClick={() => {nav(`/apartments/${path()}/reports/?page=1`)}}>Báo cáo</Nav.Link>
                        
                    </Nav>
                </Container>
            </Navbar>
            </>
        )
    }
export default ApartmentHeader