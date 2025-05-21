import axios from "axios";
import { createContext, useEffect, useState } from "react";


const QuizContext = createContext()

const QuizProvider = ({children}) => {

    const [questions, setQuestions] = useState([])
    const [validationResult, setValidationResult] = useState(null)

    async function getQuestions(){
        try{
            const response = await axios.get("/api/quiz")
            setQuestions(response.data)
        }catch(e){
            console.error("Hiba történt a kérdések megjelenítése során: ", e)
        }

    }

    async function validateAnswers(answers){
        const answersPayLoad = Object.keys(answers).map(questionId => ({
            questionId: parseInt(questionId, 10),
            answerId: answers[questionId]
        }))

        try{
            const response = await axios.post("/api/validate", answersPayLoad)
            setValidationResult(response.data)
        }catch(e){
            console.error("Hiba történt a válaszok ellenőrzése során: ", e)
        }

    }

    useEffect(() =>{
        getQuestions()
    }, [])

    return (
        <QuizContext.Provider value={{questions, validationResult, validateAnswers}}>
            {children}
        </QuizContext.Provider>
    )

}

export {QuizContext, QuizProvider}