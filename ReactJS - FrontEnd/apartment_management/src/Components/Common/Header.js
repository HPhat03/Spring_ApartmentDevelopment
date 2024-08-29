import { useContext } from 'react';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import Offcanvas from 'react-bootstrap/Offcanvas';
import { CurrentApartmentDispatcherContext, MyApartmentContext, MyUserContext, MyUserDispatchContext } from '../../configs/Context';
import { Image } from 'react-bootstrap';
import CommonStyle from './Style';
import { useNavigate } from 'react-router-dom';
import cookie from 'react-cookies'

function OffcanvasExample() {
    const user = useContext(MyUserContext)
    const apartments = useContext(MyApartmentContext)
    const setUser = useContext(MyUserDispatchContext)
    const nav = useNavigate()
    const dispatchCr = useContext(CurrentApartmentDispatcherContext);

    const navToDetail = (e, id, roomNumber) => {
        e.preventDefault()
        const payload = {roomNumber: roomNumber, id: id}
        dispatchCr({type: "change", payload: payload })
        cookie.save("current", payload, {path: "/"})
        nav(`/apartments/${id}/`)
    }
    if (user == null || user == undefined)
        return <></>
    return (
        <>
            <Navbar key={false} expand={false} bg="primary" data-bs-theme="dark">
                <Container fluid>
                    <Navbar.Brand onClick={() => {nav(`/`)}} ><h4>PNApartment.com</h4></Navbar.Brand>

                    <div style={{ ...CommonStyle.flex_space }}>
                        <Navbar.Text style={{ marginRight: 30 }}>
                            <a onClick={() => {nav(`/me/`)}} style={{ fontWeight: "bold", width: 200, textDecoration: "none", ...CommonStyle.flex_space, flexDirection: "row" }}>
                                <div className='btn'>{user.name}</div>
                                <Image className="ml-5" src={user.avatar} roundedCircle width={50} />
                            </a>
                        </Navbar.Text>

                        <Navbar.Toggle aria-controls={`offcanvasNavbar-expand-${false}`} />
                        <Navbar.Offcanvas
                            id={`offcanvasNavbar-expand-false`}
                            aria-labelledby={`offcanvasNavbarLabel-expand-false`}
                            placement="end"
                        >
                            <Offcanvas.Header closeButton>
                                <Offcanvas.Title id={`offcanvasNavbarLabel-expand-false`}>
                                    PNApartment.com
                                </Offcanvas.Title>
                            </Offcanvas.Header>
                            <Offcanvas.Body>
                                <Nav className="justify-content-end flex-grow-1 pe-3">
                                    <Nav.Link onClick={() => {nav(`/`)}} className='btn btn-info p-3 mb-2' style={{ textAlign: "left" }}>Trang chủ</Nav.Link>
                                    <Nav.Link onClick={() => {nav(`/me/`)}} className='btn btn-info p-3 mb-2' style={{ textAlign: "left" }}>Tài Khoản</Nav.Link>
                                    

                                    <NavDropdown className='p-3 mb-2' title="Chung cư của bạn" id={`offcanvasNavbarDropdown-expand-false`}>
                                        {apartments === null || apartments === undefined ? <></> : 
                                            apartments.map((x,i) => {
                                                return (
                                                    <NavDropdown.Item onClick={(e) => {navToDetail(e,x.id,x.room.roomNumber)}}><i class="bi bi-building-fill-check"></i> Phòng {x.room.roomNumber} - Tầng {x.room.roomNumber.charAt(0)}</NavDropdown.Item>
                                                )
                                            })
                                        }
                                        
                                    </NavDropdown>

                                    <Nav.Link onClick={() => {nav('/'); setUser({type: "logout"})}} className='btn btn-danger bg-danger text-light'>Đăng xuất</Nav.Link>
                                </Nav>
                            </Offcanvas.Body>
                        </Navbar.Offcanvas>
                    </div>

                </Container>
            </Navbar>
        </>
    );
}

export default OffcanvasExample;