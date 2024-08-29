
import './App.css';
import Home from './Components/Common/Home';
import Login from './Components/User/Login';
import { BrowserRouter, Route, Routes } from "react-router-dom"
import Footer from './Components/Common/footer';
import { CurrentApartmentContext, CurrentApartmentDispatcherContext, MyApartmentContext, MyApartmentDispatchContext, MyChatToggleDispatchContext, MyUserContext, MyUserDispatchContext } from './configs/Context';
import { useReducer } from 'react';
import MyUserReducer, { ChatToggleReducer, CurrentAppartmentReducer, MyApartmentReducer } from './configs/Reducers';
import cookie from "react-cookies"
import ChatBot from './Components/Special/ChatBot';
import Header from './Components/Common/Header';
import Apartment from './Components/Common/Apartment';
import Receipts from './Components/Receipt/Receipts';
import DetailReceipt from './Components/Receipt/DetailReceipt';
import RelativeRegistry from './Components/Relative Registry/RelativeRegistry';
import SmartCabinet from './Components/smart cabinet/SmartCabinet';
import ReportList from './Components/report/ReportList';
import ReportReg from './Components/report/ReportReg';
import ReportDetail from './Components/report/ReportDetail';
import SurveyReg from './Components/survey/SurveyReg';
import Validate from './Components/Receipt/Validate';

const HEIGHT = window.innerHeight;
const WIDTH = window.innerWidth;

function App() {
  const [user, dispatch] = useReducer(MyUserReducer, cookie.load("user"));
  const [showing, toggle] = useReducer(ChatToggleReducer, false);
  const [apartments, dispatchAP] = useReducer(MyApartmentReducer, cookie.load("apartments"));
  const [current, dispatchCr] = useReducer(CurrentAppartmentReducer, cookie.load("current"));

  return (
    <div style={{ position: "relative" }}>
      <BrowserRouter>
        <MyUserContext.Provider value={user}>
          <MyUserDispatchContext.Provider value={dispatch}>
            <MyChatToggleDispatchContext.Provider value={toggle} >
              <MyApartmentContext.Provider value={apartments}>
                <MyApartmentDispatchContext.Provider value={dispatchAP}>
                  <CurrentApartmentContext.Provider value={current}>
                    <CurrentApartmentDispatcherContext.Provider value={dispatchCr}>
                      <Header />
                      <Routes>
                        <Route path='/' element={<Home />} />
                        <Route path='/login/' element={<Login />} />
                        <Route path='/test/' element={<ChatBot />} />
                        <Route path='/apartments/:id/' element={<Apartment />} />
                        <Route path='/apartments/:id/receipts/' element={<Receipts/>} />
                        <Route path='/receipts/:id/' element={<DetailReceipt/>}/>
                        <Route path='/apartments/:id/relative_registries/' element={<RelativeRegistry/>} /> 
                        <Route path='/apartments/:id/cabinets/' element={<SmartCabinet/>} /> 
                        <Route path='/apartments/:id/reports/' element={<ReportList/>} /> 
                        <Route path='/apartments/:id/reports/new/' element={<ReportReg/>} /> 
                        <Route path='/reports/:id/' element={<ReportDetail/>} /> 
                        <Route path='/surveys/:id/new_response/' element= {<SurveyReg/>} />
                        <Route path='/receipts/:id/paid_handling/' element={<Validate/>} />
                      </Routes>
                      <Footer />
                    </CurrentApartmentDispatcherContext.Provider>
                  </CurrentApartmentContext.Provider>
                </MyApartmentDispatchContext.Provider>
              </MyApartmentContext.Provider>
            </MyChatToggleDispatchContext.Provider>
          </MyUserDispatchContext.Provider>
        </MyUserContext.Provider>
      </BrowserRouter>
      <div style={{ position: "absolute", width: 400, height: showing ? 550 : 50, backgroundColor: "red", left: WIDTH - 420, top: showing ? HEIGHT - 550 : HEIGHT - 50, position: "fixed", borderTopLeftRadius: 10, borderTopRightRadius: 10 }}>
        <div onClick={() => { toggle({ type: "toggle" }) }} style={{ width: "100%", height: 50, color: "white", backgroundColor: "#F6AA30", paddingLeft: 10, borderTopLeftRadius: 10, borderTopRightRadius: 10 }}>
          <div style={{ fontSize: 20, fontWeight: "bold", paddingBottom: 0 }}>PNBot Copilot</div>
          <div style={{ fontSize: 12, color: "#613C00" }}><i class="bi bi-vinyl-fill text-success"></i> Trợ lí ảo hỗ trợ dịch vụ</div>
        </div>
        <div style={{ width: "100%", height: 500, visibility: showing ? "visible" : "hidden" }}>
          <ChatBot />
        </div>
        <div></div>
      </div>

    </div>

  );
}

export default App;
