import { useContext, useState } from "react"
import { WordChainContext } from "../context/WordChainContext"
import style from "./WordChain.module.css"
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';

export default function WordChain() {

    const { words, addWord, shuffleWords } = useContext(WordChainContext)
    const [newWord, setNewWord] = useState("");

    const handleWordChange = (e) => {
        setNewWord(e.target.value);
    }

    async function handleAddWord() {
        const trimmedWord = newWord.trim()

        if (!trimmedWord) {
            alert("Érvényes szót adj meg!")
            return
        }

        if (words.length > 0) {
            const lastWord = words[words.length - 1].text
            const lastChar = lastWord.slice(-1).toLowerCase()
            const firstChar = trimmedWord[0].toLowerCase()

            if (lastChar !== firstChar) {
                alert(`A beküldőtt szó első betűjének (${firstChar}) meg kell egyeznie az előző szó utolsó betűjével (${lastChar})`)
                return
            }
        }

        try {
            await addWord(newWord)
            setNewWord("")
        } catch (e) {
            alert("Hiba történt az új szó hozzáadása során!")
            console.error("Hiba történt az új szó hozzáadása során: ", e)
        }
    }

    async function handleShuffleWords() {
        try {
            await shuffleWords()
        } catch (e) {
            alert("Hiba történt a szavak megkavarása közben!")
        }
    }

    return (
        <>
            <div className={style.container}>
                <div className={style.wordsBox}>
                    {words.map((word, index) => {
                        const isLastWord = index === words.length - 1;

                        if (isLastWord) {
                            const text = word.text;
                            const allButLast = text.slice(0, -1)
                            const lastChar = text.slice(-1)

                            return (
                                <p key={index} className={style.lastWord}>
                                    {allButLast}
                                    <span className={style.lastChar}>{lastChar}</span>
                                </p>
                            )
                        }
                        return <p key={index}>{word.text}</p>
                    }
                    )}
                </div>
                <TextField id="outlined-basic" label="Szó" variant="outlined" value={newWord} onChange={handleWordChange} />
                <Button variant="contained" onClick={handleAddWord}>Küldés</Button>
                <Button variant="contained" color="warning" onClick={handleShuffleWords}>Megkavarás</Button>
            </div>
        </>
    )

}

