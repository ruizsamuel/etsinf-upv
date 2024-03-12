import qualified Circle
import qualified Triangle
main = do
    putStrLn ("Circle.area 2 = " ++ show (Circle.area(2)))
    let triangle = Triangle.area 4 5
    putStrLn ("Triangle.area 4 5 = " ++ show (triangle))