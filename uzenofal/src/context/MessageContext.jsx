import axios from "axios";
import { createContext, useEffect, useState } from "react";


const MessageContext = createContext()

const MessageProvider = ({children}) =>{

    const [messages, setMessages] = useState([])

    async function getMessages(){
        try{
            const response = await axios.get("/api/messages")
            setMessages(response.data)
        }catch(e){
            console.error("Hiba történt az üzenetek lekérése során: ", e)
        }
    }

    async function addMessage(text){
        try{
            const response = await axios.post("/api/messages", {text})
            setMessages(response.data)
        }catch(e){
            console.error("Hiba történt az üzenet hozzáadása során:" , e)
        }
    }

    async function likeMessage(id){
        try{
            const response = await axios.post(`/api/messages/${id}/like`)
            setMessages(response.data)
        }catch(e){
            console.error("Hiba történt az üzenet kedvelése során: ", e)
        }
    }

    useEffect(() =>{
        getMessages()
    }, [])

    return(
        <MessageContext.Provider value={{messages, addMessage, likeMessage}}>
            {children}
        </MessageContext.Provider>
    )
}

export {MessageContext, MessageProvider}