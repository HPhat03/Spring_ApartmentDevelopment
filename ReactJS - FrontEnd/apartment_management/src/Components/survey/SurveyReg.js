import { useContext, useEffect, useState } from "react"
import CommonStyle from "../Common/Style"
import { MyApartmentContext, MyUserContext } from "../../configs/Context"
import { useNavigate, useParams } from "react-router-dom"
import { authApi, endpoint } from "../../configs/APIs"
import Form from 'react-bootstrap/Form';
import { Button } from "react-bootstrap"
import TextField from "@mui/material/TextField"
import Modal from "@mui/material/Modal"
import Box from '@mui/material/Box'
import UserStyle from "../User/UserStyle"
const SurveyReg = () => {
    const user = useContext(MyUserContext)
    const apartments = useContext(MyApartmentContext)
    const { id } = useParams()
    const nav = useNavigate()
    const state = ["Cực kì tệ", "Tệ", "Bình thường", "Tốt", "Cực kì tốt"]
    const band_10 = () => {
        var array = []
        for(var i=1; i<=10; i++)
            array.push(i)
        return array
    }
    const [questions, SetQuestions] = useState([])
    const [sending, SetSending] = useState({})
    const [modal, SetModal] = useState(false)
    const [error, setError] = useState("")
    const [reload, setReload] = useState(false)
    const [navable, SetNavable] = useState(false)
    useEffect(() => {
        if(user===null)
            nav('/login')
        else
            fetchQuestion()
    }, [id, reload])

    const fetchQuestion = async () => {
        const res = await authApi().get(endpoint['survey_questions'](id))
        SetQuestions(res.data)
        var answersArray = []

        res.data.map((x) => 
            answersArray.push({question_id: x.id, answer: null})
        )
        SetSending({name: "", surveyId: parseInt(id), answers: answersArray})
    }

    const setAnswer = (id, score) => {
        var answers = sending.answers;
        var added = false
        for(var i = 0; i<sending.answers.length; i++)
        {
            if(answers[i].question_id===id){
                added=true
                answers[i].answer = score
            }       
        }
        if(!added)
            answers.push({question_id: id, answer: score})
        SetSending({...sending, answers})

    }
    const submit = async (e) => {
        e.preventDefault()

        console.log(sending)
        for(var i = 0; i< sending.answers.length; i++)
        {
            console.log(sending.answers[i])
            if(sending.answers[i].answer === null)
            {
                SetModal(true)
                SetNavable(false)
                setError("Vui lòng điền đầy đủ đánh giá")
                return
            }
        }
        const res = await authApi().post(endpoint['survey_response'], sending)
        if(res.status === 200)
        {
            setError(res.data)
            SetModal(true)
        }
        else if(res.status===201){
            setError("Gửi lên thành công")
            SetModal(true)
            SetNavable(true)
        }
    }
    return <div>
        <div style={{...CommonStyle.flex_space, position: "relative", width: "100%", height: 500, backgroundImage: "url(https://cdn.getmidnight.com/113180fa10fcf7a118ecdbcd21c4cd24/2021/09/benefits-of-remote-work-og-1.jpg)", backgroundPositionY: -200, backgroundSize: "100%"}}>
                <div style={{color: "white", backgroundColor: "rgba(0, 28, 69, 0.6)", padding: 20, width: "50%"}}>
                    <h1 style={{fontSize: 60}}>PNApartment.com</h1>
                    <div>Hãy bắt đầu quản lý một căn chung cư PNA</div>
                </div>
            </div>
        <div style={{...CommonStyle.Component, height: questions===null ? 500 : questions.length*500, marginBottom: 100 , marginTop: 100}}>
                <div style={{margin: 20}}>
                    <h4>Đang thực hiện trả lời khảo sát...</h4>
                    <div>Thực hiện các khảo sát định kì giúp chúng tôi cải thiện chất lượng cuộc sống của bạn</div>
                </div>
                <Form onSubmit={submit}>
                <div style={{...CommonStyle.Box}}>
                    <div style={{...CommonStyle.Box, margin: 30, padding: 20}}>
                    <TextField required id="outlined-required" className="mt-3" value={sending.name} style={{width: "100%" }} label="Họ và tên" color="warning" onChange={(e) => SetSending({...sending, name: e.target.value})}/>
                    </div>
                    <div style={{...CommonStyle.Box, margin: 30, padding: 20}}>
                    <Form.Group className="mb-3" controlId="month">
                            <Form.Label><strong>Bạn ở chung cư số: </strong></Form.Label>
                            <Form.Select aria-label="Default select example" onChange={(e) => {if(e.target.value!=="") SetSending({...sending, apartmentId: parseInt(e.target.value)})}}>
                                <option value="" >Chọn</option>
                                {apartments===null ? <></> : apartments.map((x, i) => { return <option value={x.id}><i class="bi bi-building-fill-check"></i> Phòng {x.room.roomNumber} - Tầng {x.room.roomNumber.charAt(0)}</option> })}
                            </Form.Select>
                        </Form.Group>
                    </div>
                    {questions.map((x, i) => <div style={{...CommonStyle.Box, margin: 30, padding: 20}}>
                        <div>
                            <h4>Câu hỏi {i+1}: {x.question} <span className="text-danger">*</span></h4>
                            <div>{x.scoreBand==="BAND_10" ? "(Trên thang điểm 10, bạn đánh giá như thế nào?)" : "Đánh giá theo mức độ"}</div>
                        </div>
                        
                        {x.scoreBand === "BAND_5" ? <div style={{...CommonStyle.flex_space}}>
                        <Form style={{...CommonStyle.flex_space, marginTop: 40, width: "100%"}}>
                        {state.map((type, ind) => (<div style={{...CommonStyle.flex_center, flexDirection: "column", width: "20%"}}>
                                    <Form.Label >{type}</Form.Label>
                                    <Form.Check
                                        name="filter"
                                        type='radio'
                                        id={`inline-radio-${ind}`}
                                        value={ind+1}
                                        onChange={(e) => setAnswer(x.id, parseInt(e.target.value))}
                                    />
                                    </div>
                                ))}
                        </Form>
                        </div> : <></>}
                        {x.scoreBand === "BAND_10"? <div>
                            {band_10().map((item, ind) => <Button style={{...CommonStyle.Box, width: "50%"}} className={`btn-${sending.answers[i].score !== null && sending.answers[i].answer===item ? "primary" : "light"}`} onClick={() => setAnswer(x.id, item)}>{item}</Button>)}
                        </div> : <></>} 
                    </div>)}
                    <Button type="submit">Gửi</Button>
                </div>
                </Form>
        </div>
        <Modal open={modal} onClose={() => { SetModal(false); if(navable) nav('/')}} aria-labelledby="child-modal-title" aria-describedby="child-modal-description">
                <Box sx={{ ...UserStyle.modal, width: 600 }}>
                    <h2 id="child-modal-title" className={`text-${navable ? "success" : "danger"}`}>{navable ? "Hoàn tất khảo sát": "Thông báo lỗi"}</h2>
                    <p id="child-modal-description">
                        {error}
                    </p>
                    <Button onClick={() => { SetModal(false); if(navable) nav('/')}}>Đóng</Button>
                </Box>
            </Modal>
    </div>
}

export default SurveyReg