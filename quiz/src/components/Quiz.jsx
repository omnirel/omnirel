import { useContext, useState } from "react"
import { QuizContext } from "../context/QuizContext"
import style from "./Quiz.module.css"
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import { Typography, Button } from "@mui/material";
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import Pagination from '@mui/material/Pagination'; // Import Pagination component

export default function Quiz() {

    const { questions, validationResult, validateAnswers, setValidationResult } = useContext(QuizContext);

    const [selectedAnswers, setSelectedAnswers] = useState({});
    const [currentPage, setCurrentPage] = useState(1); // Jelenlegi oldal állapota, 1-től indexelve
    const questionsPerPage = 10; // Ennyi kérdés jelenik meg egy oldalon

    // Kiszámoljuk, mely kérdések jelenjenek meg az aktuális oldalon
    const indexOfLastQuestion = currentPage * questionsPerPage;
    const indexOfFirstQuestion = indexOfLastQuestion - questionsPerPage;
    const currentQuestions = questions.slice(indexOfFirstQuestion, indexOfLastQuestion);

    // Kiszámoljuk a lapok számát
    const totalPages = Math.ceil(questions.length / questionsPerPage);

    const handleAnswerChange = (questionId, answerId) => {
        setSelectedAnswers(prevAnswers => ({
            ...prevAnswers, [questionId]: answerId
        }));
         // Amikor a felhasználó megváltoztat egy választ, törölhetjük az előző validációs eredményt, ha volt
         if (validationResult) {
             setValidationResult(null); // Törlés a kontextusban
         }
    }

    const handleSubmit = () => {
        // Itt validálhatod, hogy minden kérdésre van-e válasz, mielőtt hívod a validateAnswers-t
        // A disabled prop a gombnál már ellenőrzi ezt.
        validateAnswers(selectedAnswers);
    }

    // Oldalváltás kezelése
    const handlePageChange = (event, value) => {
        setCurrentPage(value);
        // Opcionálisan: görgess fel az oldal tetejére lapozáskor
        window.scrollTo({ top: 0, behavior: 'smooth' });
    }

    // Kezeljük az esetet, ha nincs kérdés
    if (!questions || questions.length === 0) {
        return <Typography>Nincsenek elérhető kérdések.</Typography>;
    }

    return (
        <>
            <div className={style.container}>
                <Typography variant="h3" component="h2">
                    Quiz
                </Typography>

                <div className={style.questionCards}>
                    {/* Itt már csak a currentQuestions tömbön iterálunk */}
                    {currentQuestions.map((question) => {

                        const labelId = `question-${question.id}-label`;
                        const selectId = `question-${question.id}-select`;

                        // Megnézzük, hogy az adott kérdés helyes volt-e a validáció után
                        const questionDetail = validationResult?.details?.find(detail => detail.questionId === question.id);

                        // Kártya stílusa az eredmény alapján
                        const cardStyle = questionDetail ? {
                            minWidth: 275,
                            borderColor: questionDetail.correct ? 'green' : 'red',
                            borderWidth: '2px',
                            borderStyle: 'solid',
                            marginBottom: '20px'
                        } : {
                            minWidth: 275,
                            marginBottom: '20px'
                        };

                        return (
                            <Card key={question.id} sx={cardStyle}>
                                <CardContent>
                                    <Typography gutterBottom sx={{ color: 'text.secondary', fontSize: 14 }}>
                                        {question.text}
                                    </Typography>
                                    <FormControl fullWidth>
                                        <InputLabel id={labelId}>Válassz egy választ</InputLabel>
                                        <Select
                                            labelId={labelId}
                                            id={selectId}
                                            // Fontos: a value itt is a selectedAnswers state-ből jön, ami az összes válasz tárolja
                                            value={selectedAnswers[question.id] || ''}
                                            label="Válassz egy választ"
                                            onChange={(event) => handleAnswerChange(question.id, event.target.value)}
                                            // Letiltjuk a selectet, ha már volt validálva
                                            disabled={validationResult !== null}
                                        >
                                             <MenuItem value="">
                                                <em>Válassz egy választ</em>
                                            </MenuItem>
                                            {question.answers.map((answer) => (
                                                <MenuItem key={answer.id} value={answer.id}>
                                                    {answer.text}
                                                </MenuItem>
                                            ))}
                                        </Select>
                                    </FormControl>
                                </CardContent>
                                {/* Csak akkor jelenítjük meg a helyesség jelzést, ha van validationResult és questionDetail */}
                                {validationResult && questionDetail && (
                                    <Typography variant="body2" sx={{textAlign: 'right', padding: '0 16px 8px 0', color: questionDetail.correct ? 'green' : 'red' }}>
                                            {questionDetail.correct ? 'Helyes' : 'Helytelen'}
                                    </Typography>
                                )}
                            </Card>
                        );
                    })}
                </div>

                {/* Pagination kontrollok */}
                <div className={style.paginationContainer}>
                     <Pagination count={totalPages} page={currentPage} onChange={handlePageChange} color="primary" />
                </div>


                {/* Submit gomb - Csak akkor mutatjuk, ha az utolsó oldalon vagyunk ÉS még nem volt validálva */}
                {!validationResult && currentPage === totalPages && (
                     <Button
                        variant="contained"
                        onClick={handleSubmit}
                        sx={{ marginTop: '20px' }}
                        // Letiltjuk, ha nincs minden kérdés megválaszolva (az összes kérdés!)
                        disabled={Object.keys(selectedAnswers).length !== questions.length}
                    >
                        Ellenőrzés
                    </Button>
                )}


                {/* Eredmények megjelenítése - Csak akkor mutatjuk, ha van validationResult */}
                {validationResult && (
                    <div className={style.results}>
                        <Typography variant="h4" component="h3" sx={{ marginTop: '20px' }}>
                            Eredmények
                        </Typography>
                        <Typography>
                            Helyes válaszok: {validationResult.correct} / {validationResult.total}
                        </Typography>
                        <Button
                            variant="outlined"
                            onClick={() => {
                                setValidationResult(null); // Eredmény törlése a kontextusban
                                setSelectedAnswers({}); // Válaszok törlése a komponensben
                                setCurrentPage(1); // Vissza az első oldalra
                            }}
                             sx={{ marginTop: '10px' }}
                        >
                            Újrakezdés
                        </Button>
                    </div>
                )}
            </div>
        </>
    );
}