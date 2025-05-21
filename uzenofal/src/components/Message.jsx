import { useContext, useState } from "react"
import { MessageContext } from "../context/MessageContext"
import style from "./Message.module.css"
import Typography from '@mui/material/Typography';
import { TextField } from "@mui/material";
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import ThumbUpIcon from '@mui/icons-material/ThumbUp';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';

export default function Message() {

    const { messages, addMessage,likeMessage } = useContext(MessageContext)
    const [newMessage, setNewMessage] = useState("")

    const handleMessageChange = (e) =>{
        setNewMessage(e.target.value)
    }

    async function handleAddMessage(){
        const trimmedMessage = newMessage.trim()

        if(!trimmedMessage){
            alert("Adj meg egy üzenetet!")
            return
        }

        try{
            await addMessage(newMessage)
        }catch(e){
            console.error(e)
        }
    }

    async function handleLikeMessage(id){
        try{
            await likeMessage(id)
        }catch(e){
            console.error(e)
        }
    }

    const displayedMessages = [...messages].reverse()

    return (
        <>
            <div className={style.container}>
                <Typography variant="h4" component="div">
                    Üzenőfal
                </Typography>
                <TextField id="outlined-basic" label="Üzenet" variant="outlined" value={newMessage} onChange={handleMessageChange} fullWidth/>
                <Button variant="contained" onClick={handleAddMessage} fullWidth>Küldés</Button>
                <div className={style.messageCards}>
                    {displayedMessages.map((message) => {
                        return (
                            <Card key={message.id} sx={{ minWidth: 275, transition: "transform 0.7s ease-in-out", "&:hover":{transform: "scale(1.1)"} }}>
                                <CardContent>
                                    <Typography variant="h5" component="div">
                                        {message.text}
                                    </Typography>
                                    <Typography sx={{ color: 'text.secondary', mb: 1.5 }}>{message.likes} embernek tetszik.</Typography>
                                </CardContent>
                                <CardActions>
                                    <IconButton onClick={() =>{handleLikeMessage(message.id)}}>
                                        <ThumbUpIcon></ThumbUpIcon>
                                    </IconButton>
                                </CardActions>
                            </Card>
                        )
                    })}
                </div>
            </div>
        </>
    )

}