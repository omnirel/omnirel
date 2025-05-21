import axios from "axios";
import { createContext, useEffect, useState } from "react";


const WordChainContext = createContext()

const WordChainProivder = ({children}) =>{

    const [words, setWords] = useState([])

    async function getWords(){
        try{
            const response = await axios.get("/api/words")
            setWords(response.data)
        }catch(e){
            console.error("Hiba történt a szavak beolvasás közben: ", e)
        }
    }

    async function addWord(word){
        try{
            const response = await axios.post("/api/word", {text: word})
            setWords(response.data)
        }catch(e){
            console.error("Hiba történt az új szó hozzáadása során: ", e)
        }
    }

    async function shuffleWords(){
        try{
            const response = await axios.post("/api/shuffle")
            setWords(response.data)
        }catch(e){
            console.error("Hiba történt a szavak megkavarásánál: ", e)
        }
    }

    useEffect(() =>{
        getWords()
    }, [])

    return (
        <WordChainContext.Provider value={{words, addWord, shuffleWords}}>
            {children}
        </WordChainContext.Provider>
    )
}

export {WordChainContext, WordChainProivder}