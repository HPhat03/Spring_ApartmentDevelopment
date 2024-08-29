import '@chatscope/chat-ui-kit-styles/dist/default/styles.min.css';
import { MainContainer, ChatContainer, MessageList, Message, MessageInput, TypingIndicator } from '@chatscope/chat-ui-kit-react'
import { useState } from 'react';
import cookie from 'react-cookies'
import { getAnswer } from './NLP';

//API KEY

const ChatBot = () => {

    const [typing, setTyping] = useState(false)
    const [messages, setMessages] = useState(cookie.load("botchat") !== undefined ? cookie.load("botchat") : [
        {
            message: "Xin chào, tôi là PNBot, tôi ở đây để giải đáp thắc mắc của bạn",
            sender: "PNBot",
            direction: "incoming"
        }
    ])
    const HandleSend = async (message) => {
        const newMessages = [...messages, {
            message: message,
            sender: "user",
            direction: "outgoing",
        }]

        setMessages(newMessages)
        setTyping(true)
        const msg = getAnswer(message);
        if (msg !== "") {
            const finalmessages = [...newMessages, { message: msg, sender: "PNBot", direction: "incoming" }];
            setMessages(finalmessages)
            cookie.save("botchat", finalmessages, {path: "/"})
        }
        else if (API_KEY === "" || API_KEY === undefined) {
            const finalmessages = [...newMessages, { message: "API Key bị trống, cần bỏ API key vào", sender: "PNBot", direction: "incoming" }];
            setMessages(finalmessages)
            cookie.save("botchat", finalmessages, {path: "/"})
        }
        else
            await fetchOpenAI(newMessages)
        setTyping(false)

    }

    const fetchOpenAI = async (chatMessages) => {
        let apiMessages = chatMessages.map((x) => {
            let role = x.sender === "PNBot" ? "assistant" : "user";
            return { role: role, content: x.message }

        })
        let requestBody = {
            "model": "gpt-3.5-turbo",
            "messages": [
                ...apiMessages
            ]
        }
        await fetch("https://api.openai.com/v1/chat/completions", {
            method: "POST",
            headers: {
                "Authorization": `Bearer ${API_KEY}`,
                "Content-Type": "application/json"
            },
            body: JSON.stringify(requestBody)
        }).then((res) => { return res.json() }).then((data) => {
            const finalmessages = [...chatMessages, { message: data.choices[0].message.content, sender: "PNBot", direction: "incoming" }]
            setMessages(finalmessages)
            cookie.save("botchat", finalmessages, { path: "/" })
        })

    }
    


    return (
        <div>
            <div style={{ position: "relative", height: 500, width: "100%" }}>
                <MainContainer>
                    <ChatContainer>
                        <MessageList typingIndicator={typing ? <TypingIndicator content="PNBot đang trả lời" /> : null}>
                            {messages.map((x, i) => {
                                return <Message key={i} model={x} />
                            })}
                        </MessageList>
                        <MessageInput placeholder='Nhập vào câu hỏi của bạn' onSend={HandleSend} />
                    </ChatContainer>
                </MainContainer>
            </div>
        </div>
    );
}

export default ChatBot