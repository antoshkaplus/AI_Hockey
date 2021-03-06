/**
 * Created by antoshkaplus on 9/11/14.
 */

import static java.lang.Math.*;

/** a lot of global shit here
 * probably some util functions */

public class AI {
    public static final double COMPUTATION_BIAS = 1e-4;
    public static final double DEGREE = PI / 180;

    public static AIPoint unit(double angle) {
        return new AIPoint(cos(angle), sin(angle));
    }

    public static AIPoint vector(double angle, double distance) {
        AIPoint p = unit(angle);
        p.scale(distance);
        return p;
    }

    // if oriented orientAngle needed please use world orientedAngle

    // three point orientAngle
    // orientAngle between [0, PI]
    public static double angle(AIPoint p_one, AIPoint p_center, AIPoint p_two) {
        double a = p_one.distance(p_center);
        double b = p_two.distance(p_center);
        double c = p_one.distance(p_two);
        // 0, PI
        double dd = a * a + b * b - c * c;
        double ff = 2 * a * b;
        double rr;
        if (abs(dd - ff) < COMPUTATION_BIAS) {
            rr = 1.0 * signum(dd*ff);
        } else {
            rr = dd / ff;
        }
        return Math.acos(rr);
    }

    // angle between two vectors [0, PI]
    public static double angle(AIPoint v_one, AIPoint v_two) {
        return angle(v_one, AIPoint.ZERO, v_two);
    }


    // oriented orientAngle
    public static double orientAngle(AIPoint p) {
        return atan2(p.y, p.x);
    }

    /** where should turn */
    public static double orientAngle(double source, double target) {
        target -= source;
        if (target < -PI) target += 2*PI;
        if (target >  PI) target -= 2*PI;
        return target;
    }

    public static double orientAngle(AIPoint v_source, AIPoint v_target) {
        return orientAngle(orientAngle(v_source), orientAngle(v_target));
    }

    // get in some orientAngle [0 2PI] or negative
    // return orientAngle between -PI, PI
    public static double orientAngle(double angle) {
        if (angle > PI) {
            return angle - 2*PI;
        }
        if (angle < -PI) {
            return angle + 2*PI;
        }
        return angle;
    }

    public static boolean isSegmentIntersectRay(AILine segment, AIPoint center, double angle) {
        // orientAngle line
        // it's much better to implement this through degrees
        AILine a = new AILine(center, angle);
        AIPoint p = segment.intersection(a);
        return isBetween(segment.one, segment.two, p);
    }

    public static boolean isBetween(AIPoint one, AIPoint two, AIPoint between) {
        double a = one.distance(two);
        double b = between.distance(one) + between.distance(two);
        return abs(a - b) < COMPUTATION_BIAS;
    }


    public static boolean isValueBetween(double one, double two, double between) {
        return (one <= between && between <= two) || (one >= between && between >= two);
    }

    /** angles should be  */
    public static boolean isAngleBetween(double one, double two, double between) {
        return orientAngle(between, one) * orientAngle(between, two) <= 0;
    }

    public static boolean isOriented(double angle) {
        return isValueBetween(-PI, PI, angle);
    }

    public static AIPoint projection(AIPoint ofVector, AIPoint ontoVector) {
        AIPoint result = new AIPoint(ontoVector);
        result.scale(AIPoint.dotProduct(ofVector, ontoVector)
                /AIPoint.dotProduct(ontoVector, ontoVector));
        return result;
    }

    public static double projectionScalar(AIPoint ofVector, AIPoint ontoVector) {
        return AIPoint.dotProduct(ofVector, ontoVector)/ontoVector.distance(AIPoint.ZERO);
    }
}

