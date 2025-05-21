import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { QuizProvider } from './context/QuizContext'
import Quiz from './components/Quiz'

function App() {
 
  return (
    <>
      <QuizProvider>
        <Quiz></Quiz>
      </QuizProvider>
    </>
  )
}

export default App
