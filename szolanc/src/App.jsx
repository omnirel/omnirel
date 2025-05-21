
import './App.css'
import WordChain from './components/WordChain'
import { WordChainProivder } from './context/WordChainContext'

function App() {

  return (
    <WordChainProivder>
      <WordChain></WordChain>
    </WordChainProivder>
  )
}

export default App
